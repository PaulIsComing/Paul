package com.yuezhou.shrioboot.shrio;

import com.yuezhou.shrioboot.po.CurrentUser;
import com.yuezhou.shrioboot.po.UserInfo;
import com.yuezhou.shrioboot.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JWTRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userId = JWTUtil.getUserId(principalCollection.toString());
        assert null != userId && userId > 0L;
        UserInfo userInfo = userService.getUser(userId);
        assert null != userInfo;

        if (null == CurrentUser.getUser()) {
            CurrentUser.setUser(userInfo);
        }

        ShrioAuthenticationInfo authenticationInfo = new ShrioAuthenticationInfo();
        authenticationInfo.setRoles(userInfo.getRole());
        authenticationInfo.addStringPermissions(userInfo.getPremisson());
        return authenticationInfo;
    }

    @Override
    /**
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String token = (String) authenticationToken.getCredentials();

        Long userId = JWTUtil.getUserId(token);

        if (null == userId) {
            throw new AuthenticationException("Token失效");
        }

        UserInfo userInfo = userService.getUser(userId);
        if (null == userInfo) {
            throw new AuthenticationException("用户不存在");
        }

        if (!JWTUtil.verify(token, userId, userInfo.getPassword())) {
            throw new AuthenticationException("登录认证失败");
        }

        return new SimpleAuthenticationInfo(token, token, "JWTRealm");
    }


    @Override
    public void onLogout(PrincipalCollection principals){
        super.onLogout(principals);
    }
}
