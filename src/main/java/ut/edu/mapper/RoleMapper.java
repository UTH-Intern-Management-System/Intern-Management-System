package ut.edu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ut.edu.dto.request.RoleRequest;
import ut.edu.dto.response.RoleResponse;
import ut.edu.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
