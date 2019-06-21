package com.idiot.blog.repository;

import com.idiot.blog.common.CommonRepository;
import com.idiot.blog.entity.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName:TbUserRepository
 * @Description:TODO
 * @Version:1.0
 **/
@Repository
public interface TbUserRepository extends CommonRepository<TbUser,Integer> {
}
