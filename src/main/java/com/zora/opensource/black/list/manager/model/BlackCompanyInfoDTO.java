package com.zora.opensource.black.list.manager.model;

import lombok.Data;

import java.util.List;

/**
 * <h3>black_list_manager</h3>
 * <h4>com.zora.opensource.black.list.manager.model</h4>
 * <p>api dto</p>
 *
 * @author Yuhan.Ji
 * @since 2021.06.03
 */
@Data
public class BlackCompanyInfoDTO {
    private Integer id;
    private String name;
    private String address;
    private String business;
    private String reason;
    private List<String> comments;
}
