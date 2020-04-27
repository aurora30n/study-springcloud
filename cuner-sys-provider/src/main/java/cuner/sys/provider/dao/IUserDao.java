package cuner.sys.provider.dao;

import cuner.sys.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserDao {

    public long insert(User user);

    public User findByUsername(@Param("username") String username);

    public List<User> findList(User user);

    /*public long deleteByIds(@Param("list") List<Long> ids);*/

}
