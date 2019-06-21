package com.idiot.blog.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @ClassName:CommonRepository
 * @Description:TODO
 * @Version:1.0
 **/
@NoRepositoryBean
public interface CommonRepository<T,ID> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {
}
