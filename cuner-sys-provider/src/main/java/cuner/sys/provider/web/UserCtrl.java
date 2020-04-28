package cuner.sys.provider.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cuner.common.req.ResCode;
import cuner.common.req.ResData;
import cuner.common.util.IdGen;
import cuner.common.web.BaseCtrl;
import cuner.sys.common.entity.User;
import cuner.sys.common.vo.UserVo;
import cuner.sys.provider.dao.IUserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserCtrl extends BaseCtrl {

    @Resource
    private IUserDao userDao;

    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public ResData<Object> reg(@RequestBody UserVo userVo) {
        try {
            String regValid = vaildRegUser(userVo);
            if (regValid!=null) {
                return ResData.error(regValid);
            }

            User dbUser = this.userDao.findByUsername(userVo.getUsername());
            if (dbUser!=null) {
                return ResData.error("用户名已存在");
            }
            User user = new User();
            BeanUtils.copyProperties(userVo, user);
            user.setId(IdGen.nextId());
            long i = this.userDao.insert(user);
            if (i>0) {
                return ResData.success("注册成功");
            } else {
                return ResData.error(ResCode.failDbOpt);
            }
        } catch (Exception e) {
            log.error("系统错误", e);
            return ResData.error(ResCode.failSysError);
        }
    }

    private String vaildRegUser(UserVo userVo) {
        if (StringUtils.isBlank(userVo.getUsername())) {
            return "用户名不能为空";
        }
        if (StringUtils.isBlank(userVo.getPwd())) {
            return "密码不能为空";
        }
        if (!userVo.getPwd().equals(userVo.getConfirmPwd())) {
            return "两次输入密码不一致";
        }
        return null;
    }

    @GetMapping("/user/login/{username}")
    public ResData<User> findByUsername(@PathVariable String username) {
        try {
            User user = this.userDao.findByUsername(username);
            return ResData.success(user);
        } catch (Exception e) {
            log.error("系统错误", e);
            return ResData.error(ResCode.failSysError);
        }
    }

    @GetMapping("/user/page/{pageNo}/{pageSize}")
    public ResData<PageInfo> page(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        try {
            PageHelper.startPage(pageNo, pageSize);
            List<User> list = this.userDao.findList(new User());
            PageInfo<User> page = new PageInfo<>(list);
            return ResData.success(page);
        } catch (Exception e) {
            log.error("系统错误", e);
            return ResData.error(ResCode.failSysError);
        }
    }

    @PostMapping("/user/login")
    public ResData<Object> login(HttpServletRequest request, @RequestBody User user) {
        try {
            User dbUser = this.userDao.findByUsername(user.getUsername());
            if (dbUser==null) {
                return ResData.error("用户不存在");
            }
            if (!dbUser.getPwd().equals(user.getPwd())) {
                return ResData.error("密码错误");
            }
            request.getSession().setAttribute("user", dbUser);
            return ResData.success("登录成功");
        } catch (Exception e) {
            log.error("系统错误", e);
            return ResData.error(ResCode.failSysError);
        }
    }

}
