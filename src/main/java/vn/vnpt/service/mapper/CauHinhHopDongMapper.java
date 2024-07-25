package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CauHinhHopDong;
import vn.vnpt.service.dto.CauHinhHopDongDTO;

/**
 * Mapper for the entity {@link CauHinhHopDong} and its DTO {@link CauHinhHopDongDTO}.
 */
@Mapper(componentModel = "spring")
public interface CauHinhHopDongMapper extends EntityMapper<CauHinhHopDongDTO, CauHinhHopDong> {}
