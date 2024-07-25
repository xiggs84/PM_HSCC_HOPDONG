package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CauHinhMauHopDong;
import vn.vnpt.service.dto.CauHinhMauHopDongDTO;

/**
 * Mapper for the entity {@link CauHinhMauHopDong} and its DTO {@link CauHinhMauHopDongDTO}.
 */
@Mapper(componentModel = "spring")
public interface CauHinhMauHopDongMapper extends EntityMapper<CauHinhMauHopDongDTO, CauHinhMauHopDong> {}
