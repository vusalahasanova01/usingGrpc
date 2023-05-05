package com.example.demo.mapper;

import com.example.demo._2a.tables.records.UserMenuJoinRecord;
import com.example.demo.dto.UserMenuRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuRoleMapper {

    MenuRoleMapper INSTANCE = Mappers.getMapper(MenuRoleMapper.class);

    default List<UserMenuJoinRecord> toMenuRole(List<UserMenuRole> userMenuRoles, Long userId) {
        return userMenuRoles.stream()
                .map(roles -> {
                    UserMenuJoinRecord userMenuJoinRecord = new UserMenuJoinRecord();
                    userMenuJoinRecord.setUserId(userId);
                    userMenuJoinRecord.setMenuId(roles.getMenuId());
                    userMenuJoinRecord.setRoleId(roles.getRoleId());
                    return userMenuJoinRecord;
                }).collect(Collectors.toList());

    }

}
