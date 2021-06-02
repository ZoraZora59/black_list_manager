package com.zora.opensource.black.list.manager.controller;

import com.zora.opensource.black.list.manager.model.BlackCompanyInfo;
import com.zora.opensource.black.list.manager.repository.IBlackListRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<List<BlackCompanyInfo>> getBlackList(@RequestParam(required = false) String keyword) {
        try {
            List<BlackCompanyInfo> repoResult = blackListRepo.select(keyword);
            return ResponseEntity.ok(repoResult);
        }catch (Exception ex){
            log.error("select fail",ex);
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> updateOne(@RequestParam BlackCompanyInfo info) {
        try {
            Integer rows= blackListRepo.upsert(info);
            return ResponseEntity.ok(rows);
        }catch (Exception ex){
            log.error("upsert fail",ex);
            return ResponseEntity.status(500).body(0);
        }
    }

}
