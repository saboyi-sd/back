package com.office.yancao.service;

import com.office.yancao.dto.NoticeDTO;
import com.office.yancao.dto.NoticeRespDto;
import com.office.yancao.dto.UnreadUserDTO;
import com.office.yancao.entity.*;
import com.office.yancao.mapper.FaultImageMapper;
import com.office.yancao.mapper.NoticeMapper;
import com.office.yancao.mapper.UserDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    @Value("${file.upload-path}")
    private String uploadPath;

    @Value("${file.base-url}")
    private String baseUrl;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private UserDepartmentMapper userDepartmentMapper;

    @Autowired
    private FaultImageMapper faultImageMapper;

    public void publishNotice(NoticeDTO dto) throws IOException {
        Date date = new Date();
        Notice notice = new Notice();
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        notice.setType(dto.getType());
        notice.setCreatedBy(dto.getUsername());
        notice.setCreatedAt(date);
        notice.setIsDeleted(false);
        List<String> images = dto.getImages();
        String imageStr = null;

        if (images != null && !images.isEmpty()) {
            imageStr = String.join(",", images);
        }

        notice.setImages(imageStr);
        noticeMapper.insertNotice(notice);

        System.out.println(dto.getImages());



        NoticeReceiver noticeReceiver = new NoticeReceiver();
        noticeReceiver.setNoticeId(notice.getId());
        noticeReceiver.setNoticeTime(date);
        noticeReceiver.setIsRead(0);
        noticeReceiver.setUserId(dto.getUserId());
        // noticeMapper.insertReceiver(noticeReceiver);

        Set<Long> targetUserIds = new HashSet<>();

        Set<Long> result = new HashSet<>();

        if ("ALL".equals(dto.getType())) {
            GroupUser groupUser = userDepartmentMapper.selectDepartmentId(dto.getUserId());
            collectChildIds(groupUser.getGroupId(), result);
            List<Long> userIdsByDeptIds = userDepartmentMapper.getUserIdsByDeptIds(result);
            // List<User> users = noticeMapper.listUsers();
            targetUserIds.addAll(userIdsByDeptIds);
        }
        else if ("DEPT".equals(dto.getType()) && dto.getSelectedDeptIds() != null) {
            for (Long deptId : dto.getSelectedDeptIds()) {
                List<GroupUser> groupUsers = noticeMapper.getUsersByDeptId(deptId);
                targetUserIds.addAll(groupUsers.stream().map(GroupUser::getUserId).collect(Collectors.toList()));
            }
            noticeMapper.insertReceiver(noticeReceiver);
        }
        else if ("SELECTED".equals(dto.getType()) && dto.getSelectedUserIds() != null) {
            targetUserIds.addAll(dto.getSelectedUserIds());
            noticeMapper.insertReceiver(noticeReceiver);
        }

        if (!targetUserIds.isEmpty()) {
            List<NoticeReceiver> receivers = targetUserIds.stream()
                    .distinct()
                    .map(userId -> {
                        NoticeReceiver r = new NoticeReceiver();
                        r.setNoticeId(notice.getId());
                        r.setUserId(userId);
                        r.setIsRead(0);
                        r.setNoticeTime(date);
                        return r;
                    }).collect(Collectors.toList());
            noticeMapper.insertReceivers(receivers);
        }
    }

    public List<Department> listDepartments(long id) {
        GroupUser groupUser = userDepartmentMapper.selectDepartmentId(id);
        return userDepartmentMapper.getDirectChildren(groupUser.getGroupId());
    }


    public List<NoticeRespDto> listNoticesForUser(Long userId) {
        List<NoticeRespDto> res = noticeMapper.listNoticesForUser(userId);
        // return noticeMapper.listNoticesForUser(userId, startTime, endTime);
        return res;
    }

    public ReadStats getReadStats(Long noticeId) {
        return noticeMapper.getReadStats(noticeId);
    }

    public Notice getNoticeDetail(Long noticeId) {
        Notice noticeDetail = noticeMapper.getNoticeDetail(noticeId);
        String imageStr = noticeDetail.getImages();
        List<String> images = (imageStr == null || imageStr.isEmpty())
                ? Collections.emptyList()
                : Arrays.asList(imageStr.split(","));
        noticeDetail.setImagesUrl(images);
        return noticeDetail;
    }

    public void markAsRead(Long noticeId, Long userId) {
        noticeMapper.setMarkAsRead(noticeId, userId);
    }

    public Map<String, Object> getUnreadUsers(Long noticeId) {

        // 2. 查询未读用户
        List<UnreadUserDTO> unreadUsers = noticeMapper.selectUnreadUsersByNoticeId(noticeId);

        // 3. 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("users", unreadUsers);
        result.put("unreadCount", unreadUsers.size());

        return result;
    }

    private void collectChildIds(Long deptId, Set<Long> result) {
        result.add(deptId); // 先把自己加进去

        // 查询直接子部门
        List<Long> children = userDepartmentMapper.getDirectChildDeptIds(deptId);
        for (Long childId : children) {
            collectChildIds(childId, result); // 递归
        }
    }

    public List<Article> listPromotion() {
        List<Article> res = noticeMapper.listPromotion();
        // return noticeMapper.listNoticesForUser(userId, startTime, endTime);
        return res;
    }

    public void updatePromotion(Long articleId, Boolean isBanner) {
        if (isBanner){
            noticeMapper.updatePromotion(articleId, 1);
        }else {
            noticeMapper.updatePromotion(articleId, 0);
        }
    }

    public int saveArticle(Article article) {
        if (!article.getCoverImages().isEmpty()){
            String image = article.getCoverImages().get(0);
            System.out.println(image);
            article.setCoverImage(image);
        }
        return noticeMapper.insertArticle(article);
    }

    public List<Article> getArticleBanner() {
        List<Article> res = noticeMapper.getArticleBanner();
        // return noticeMapper.listNoticesForUser(userId, startTime, endTime);
        return res;
    }

    public Article getArticleDetail(Long id) {
        return noticeMapper.getArticleDetail(id);
    }
}