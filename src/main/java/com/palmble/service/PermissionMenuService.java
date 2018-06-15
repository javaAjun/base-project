package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.Page;

public interface PermissionMenuService {
	Page<Object> getMenuList(Map<String, String> map);
}
