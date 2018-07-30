package cn.iruier.sso.provider;

import cn.iruier.core.jedis.JedisUtil;
import cn.iruier.core.util.EncrypUtil;
import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.IdGenerator;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.User;
import cn.iruier.mapper.user.UserMapper;
import cn.iruier.sso.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserProvider implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ResultVo save(User user) {
        user.setPassword(EncrypUtil.md5Pass(user.getPassword()));
        return ExecuteUtil.getResult(mapper.insert(user), "用户注册");
    }

    @Override
    public ResultVo queryByName(String name, String password) {
        User user = mapper.queryByName(name);
        if (user != null) {
            password = EncrypUtil.md5Pass(password);
            if (Objects.equals(password, user.getPassword())) {
                long token = idGenerator.nextId();
                jedisUtil.addStr("userToken:"+ token, JSON.toJSONString(user));
                jedisUtil.expire("userToken:"+ token, TimeUnit.MINUTES, 30);
                return new ResultVo(0, token+"", user);
            } else {
                return ResultVo.setError("登录密码错误");
            }
        }
        return ResultVo.setError("用户名不存在");
    }

    @Override
    public ResultVo checkLogin(String token) {
        if (jedisUtil.isKey("userToken:" + token)) {
            jedisUtil.expire("userToken:"+token, TimeUnit.MINUTES, 30);
            return ResultVo.setSuccess("有效");
        } else {
            return ResultVo.setError("失效");
        }
    }

    @Override
    public ResultVo loginOut(String token) {
        jedisUtil.delKey("userToken:" + token);
        return ResultVo.setSuccess("OK");
    }

    @Override
    public ResultVo checkName(String name) {
        if (mapper.queryByName(name) != null) {
            return ResultVo.setError("用户名已存在");
        } else {
            return ResultVo.setSuccess("用户名可用");
        }
    }

}
