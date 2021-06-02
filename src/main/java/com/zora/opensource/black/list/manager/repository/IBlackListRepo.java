package com.zora.opensource.black.list.manager.repository;

import com.zora.opensource.black.list.manager.model.BlackCompanyInfo;

import java.util.Collection;
import java.util.List;

/**
 * <h3>black_list_manager</h3>
 * <h4>com.zora.opensource.black.list.manager.repository</h4>
 * <p>repo api</p>
 *
 * @author Yuhan.Ji
 * @since 2021.06.02
 */
public interface IBlackListRepo {
    /**
     * select
     * @param filterKeyword company name keyword(optional)
     * @return all matched rows
     */
    List<BlackCompanyInfo> select(String filterKeyword);

    /**
     * Insert or update
     * @param info company info
     * @return affect rows
     */
    int upsert(BlackCompanyInfo info);

    /**
     * delete
     * @param idList id set
     * @return affect rows
     */
    int delete(Collection<Integer> idList);
}
