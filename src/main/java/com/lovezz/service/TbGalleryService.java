package com.lovezz.service;

import com.lovezz.dto.GalleryVo;
import com.lovezz.entity.TbGallery;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liweijian123
 * @since 2019-10-12
 */
public interface TbGalleryService extends IService<TbGallery> {

    String fileUpload(MultipartFile file) throws MalformedURLException;
    String fileUpload(MultipartFile file, String flag) throws MalformedURLException;
    List<String> fileUpload(MultipartFile[] file, String flag) throws MalformedURLException;

    List<TbGallery> selectGalleryList(Integer action, Integer page);

    GalleryVo selectGalleryWrapper();

    /**
     * 构建图库对象
     * @return
     */
    TbGallery makeGallery(String url, String topsId, String flag, String fileName);


}
