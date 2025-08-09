package ut.edu.mapper;

import org.mapstruct.Mapper;

import ut.edu.dto.request.PermissionRequest;
import ut.edu.dto.response.PermissionResponse;
import ut.edu.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
