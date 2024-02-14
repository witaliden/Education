package com.wd.education.api.security.permission;

import java.util.List;

public interface PermissionService {

  List<String> getPermissions(String token, String permissionsUrl);
}
