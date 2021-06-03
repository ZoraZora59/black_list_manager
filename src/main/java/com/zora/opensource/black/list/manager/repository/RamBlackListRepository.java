//package com.zora.opensource.black.list.manager.repository;
//
//import com.zora.opensource.black.list.manager.model.BlackCompanyInfo;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * <h3>black_list_manager</h3>
// * <h4>com.zora.opensource.black.list.manager.repository</h4>
// * <p>ram based   for test </p>
// *
// * @author Yuhan.Ji
// * @since 2021.06.02
// */
////@Repository
//public class RamBlackListRepository implements IBlackListRepo {
//    private final Map<Integer, BlackCompanyInfo> database = new ConcurrentHashMap<>();
//    private final AtomicInteger idCounter = new AtomicInteger(0);
//
//    /**
//     * select
//     *
//     * @param filterKeyword company name keyword(optional)
//     * @return all matched rows
//     */
//    @Override
//    public List<BlackCompanyInfo> select(String filterKeyword) {
//        if (Strings.isBlank(filterKeyword)) {
//            return new ArrayList<>(database.values());
//        }
//        List<BlackCompanyInfo> response = new LinkedList<>();
//        for (BlackCompanyInfo info : database.values()) {
//            if (info.getName().contains(filterKeyword)) {
//                response.add(info);
//            }
//        }
//        return response;
//    }
//
//    /**
//     * Insert or update
//     *
//     * @param info company info
//     * @return affect rows
//     */
//    @Override
//    public int upsert(BlackCompanyInfo info) {
//        if (Objects.isNull(info.getId())) {
//            info.setId(idCounter.incrementAndGet());
//        }
//        database.put(info.getId(), info);
//        return 1;
//    }
//
//    /**
//     * delete
//     *
//     * @param idList id set
//     * @return affect rows
//     */
//    @Override
//    public int delete(Collection<Integer> idList) {
//        int counter=0;
//        for (Integer id : idList) {
//            database.remove(id);
//            counter++;
//        }
//        return counter;
//    }
//}
