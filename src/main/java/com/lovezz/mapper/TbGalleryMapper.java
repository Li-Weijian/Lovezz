package com.lovezz.mapper;

import com.lovezz.entity.TbGallery;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liweijian123
 * @since 2019-10-12
 */
public interface TbGalleryMapper extends BaseMapper<TbGallery> {

    List<String> selectGalleryWrapper();

}
