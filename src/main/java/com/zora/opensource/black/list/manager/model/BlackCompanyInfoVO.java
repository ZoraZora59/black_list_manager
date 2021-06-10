package com.zora.opensource.black.list.manager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <h3>black_list_manager</h3>
 * <h4>com.zora.opensource.black.list.manager.model</h4>
 * <p>api dto</p>
 *
 * @author Yuhan.Ji
 * @since 2021.06.03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlackCompanyInfoVO extends BlackCompanyInfoDTO {
    private LocalDateTime updateTime;
}
