package com.zora.opensource.black.list.manager.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import com.zora.opensource.black.list.manager.model.BlackCompanyInfoDTO;
import com.zora.opensource.black.list.manager.model.ExcelModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.util.Strings;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h3>black_list_manager</h3>
 * <h4>com.zora.opensource.black.list.manager.utils</h4>
 * <p>Read company info from excel</p>
 *
 * @author Yuhan.Ji
 * @since 2021.06.03
 */
@Slf4j
public class ExcelReader {
    private static final String FILE = "/Users/bytedance/Downloads/济南软件公司黑名单20210602.xlsx";

    public static void main(String[] args) {
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(FILE, ExcelModel.class, new MyListener()).sheet().doRead();

    }

    static class MyListener extends AnalysisEventListener<ExcelModel> {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        private AtomicInteger counter = new AtomicInteger(0);
        private AtomicInteger validCounter = new AtomicInteger(0);

        @Override
        public void invoke(ExcelModel excelModel, AnalysisContext analysisContext) {
            log.info(excelModel.toString());
            if (Strings.isNotBlank(excelModel.getName())) {
                String api = "/api/";
                String base = "http://localhost:34215";
                String url = base + api;
                HttpPost httpPost = new HttpPost(url);
                BlackCompanyInfoDTO dto = new BlackCompanyInfoDTO();
                dto.setId(excelModel.getId());
                dto.setName(excelModel.getName());
                dto.setAddress(excelModel.getAddress());
                dto.setBusiness(excelModel.getBusiness());
                dto.setReason(excelModel.getReason());
                dto.setComments(Collections.singletonList(excelModel.getFirstComment()));
                httpPost.setHeader("Content-Type", "application/json;charset=utf8");
                httpPost.setEntity(new StringEntity(JSONObject.toJSONString(dto), "UTF-8"));
                try {
                    CloseableHttpResponse response = httpClient.execute(httpPost);
                    log.info(response.toString());
                    response.close();
                    validCounter.incrementAndGet();
                } catch (Exception ex) {
                    log.error("http post error", ex);
                }
            }
            counter.incrementAndGet();
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            log.info("AnalysisSuccess. Total {} rows, {} rows valid.", counter.get(), validCounter.get());

        }
    }
}
