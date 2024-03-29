package com.zora.opensource.black.list.manager.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <h3>black_list_manager</h3>
 * <h4>com.zora.opensource.black.list.manager.model</h4>
 * <p>黑名单信息</p>
 *
 * @author Yuhan.Ji
 * @since 2021.06.02
 */
@Data
public class BlackCompanyInfoEntity {
    private Integer id;
    private String name;
    private String address;
    private String business;
    private String reason;
    private String comments;
    private LocalDateTime updateTime;
}
