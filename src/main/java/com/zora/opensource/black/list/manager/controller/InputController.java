package com.zora.opensource.black.list.manager.controller;

import com.alibaba.fastjson.JSON;
import com.zora.opensource.black.list.manager.model.BlackCompanyInfoDTO;
import com.zora.opensource.black.list.manager.model.BlackCompanyInfoEntity;
import com.zora.opensource.black.list.manager.model.BlackCompanyInfoVO;
import com.zora.opensource.black.list.manager.repository.IBlackListRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h3>black_list_manager</h3>
 * <h4>com.zora.opensource.black.list.manager.controller</h4>
 * <p>UserInput</p>
 *
 * @author Yuhan.Ji
 * @since 2021.06.02
 */
@Slf4j
@RestController("/blacklist")
public class InputController {
    @Autowired
    private IBlackListRepo blackListRepo;

    @GetMapping("/")
    public ResponseEntity<List<BlackCompanyInfoVO>> getBlackList(@RequestParam(required = false) String keyword) {
        try {
            List<BlackCompanyInfoEntity> repoResult = blackListRepo.select(keyword);
            List<BlackCompanyInfoVO> voList = new ArrayList<>(repoResult.size());
            for (BlackCompanyInfoEntity entity : repoResult) {
                BlackCompanyInfoVO vo = new BlackCompanyInfoVO();
                vo.setId(entity.getId());
                vo.setName(entity.getName());
                vo.setReason(entity.getReason());
                vo.setAddress(entity.getAddress());
                vo.setBusiness(entity.getBusiness());
                vo.setUpdateTime(entity.getUpdateTime());
                vo.setComments(JSON.parseArray(entity.getComments(), String.class));
                voList.add(vo);
            }
            return ResponseEntity.ok(voList);
        } catch (Exception ex) {
            log.error("select fail", ex);
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> updateOne(@RequestParam BlackCompanyInfoDTO info) {
        try {
            BlackCompanyInfoEntity entity = new BlackCompanyInfoEntity();
            entity.setId(info.getId());
            entity.setAddress(info.getAddress());
            entity.setBusiness(info.getBusiness());
            entity.setReason(info.getReason());
            entity.setName(info.getName());
            entity.setComments(JSON.toJSONString(info.getComments()));
            Integer rows = blackListRepo.upsert(entity);
            return ResponseEntity.ok(rows);
        } catch (Exception ex) {
            log.error("upsert fail", ex);
            return ResponseEntity.status(500).body(0);
        }
    }

}
