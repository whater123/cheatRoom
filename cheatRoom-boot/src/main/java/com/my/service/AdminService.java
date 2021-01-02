package com.my.service;

import com.my.pojo.base.Key;
import com.my.pojo.base.Notice;
import com.my.pojo.view.KeyShow;

import java.util.List;

/**
 * @author w
 */
public interface AdminService {
    boolean addAdminNotice(Notice notice);

    boolean deleteNotice(int noticeId);

    String getVipKey();

    List<KeyShow> getAllKeys();
}
