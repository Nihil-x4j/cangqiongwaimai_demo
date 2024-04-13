package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: DishFlavorMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author x4j
 * @Create 2024/4/12 23:07
 * @Version 1.0
 */
@Mapper
public interface DishFlavorMapper {


    void insertBatch(List<DishFlavor> flavors);
    @Select(value = "select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> queryDishFlavor(Long dishId);

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    void delByIds(List<Long> ids);
}
