package com.gec.mapper;

import com.gec.pojo.TypeInf;
import com.gec.pojo.TypeInfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeInfMapper {
    int countByExample(TypeInfExample example);

    int deleteByExample(TypeInfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TypeInf record);

    int insertSelective(TypeInf record);

    List<TypeInf> selectByExample(TypeInfExample example);

    TypeInf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TypeInf record, @Param("example") TypeInfExample example);

    int updateByExample(@Param("record") TypeInf record, @Param("example") TypeInfExample example);

    int updateByPrimaryKeySelective(TypeInf record);

    int updateByPrimaryKey(TypeInf record);
}