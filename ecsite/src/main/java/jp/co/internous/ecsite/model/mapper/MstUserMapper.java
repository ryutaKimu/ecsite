package jp.co.internous.ecsite.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.ecsite.model.domain.MstUser;
import jp.co.internous.ecsite.model.form.LoginForm;
import jp.co.internous.ecsite.model.form.RegisterForm;

@Mapper
public interface MstUserMapper {
	@Select(value="SELECT * FROM mst_user WHERE user_name=#{userName} and password = #{password}")
	 MstUser findByUserNameAndPassword(LoginForm form);
	
	@Insert("INSERT INTO mst_user(user_name, password, full_name, is_admin) VALUES (#{userName}, #{password}, #{fullName}, #{isAdmin})")
    int insert(RegisterForm form);

}
