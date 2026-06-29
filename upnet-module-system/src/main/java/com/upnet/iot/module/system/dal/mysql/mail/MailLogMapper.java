package com.upnet.iot.module.system.dal.mysql.mail;

import cn.hutool.core.util.StrUtil;
import com.upnet.iot.framework.common.pojo.PageResult;
import com.upnet.iot.framework.mybatis.core.mapper.BaseMapperX;
import com.upnet.iot.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.upnet.iot.framework.mybatis.core.util.MyBatisUtils;
import com.upnet.iot.module.system.controller.admin.mail.vo.log.MailLogPageReqVO;
import com.upnet.iot.module.system.dal.dataobject.mail.MailLogDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailLogMapper extends BaseMapperX<MailLogDO> {

    default PageResult<MailLogDO> selectPage(MailLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MailLogDO>()
                .eqIfPresent(MailLogDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MailLogDO::getUserType, reqVO.getUserType())
                .eqIfPresent(MailLogDO::getAccountId, reqVO.getAccountId())
                .eqIfPresent(MailLogDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(MailLogDO::getSendStatus, reqVO.getSendStatus())
                .betweenIfPresent(MailLogDO::getSendTime, reqVO.getSendTime())
                .apply(StrUtil.isNotBlank(reqVO.getToMail()),
                        MyBatisUtils.findInSet("to_mails"), reqVO.getToMail())
                .orderByDesc(MailLogDO::getId));
    }

}
