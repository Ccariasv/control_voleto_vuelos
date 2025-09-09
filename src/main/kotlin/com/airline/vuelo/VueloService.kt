package com.airline.vuelo

import com.airline.avion.AvionService
import com.airline.exception.NotFoundException
import com.airline.ruta.RutaService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class VueloService(
    private val vueloRepository: VueloRepository,
    private val rutaService: RutaService,
    private val avionService: AvionService,
) {
    fun findAll(): List<VueloDto> = vueloRepository.findAll().map { it.toDto() }

    fun findById(id: Long): VueloDto = findEntityById(id).toDto()

    fun findEntityById(id: Long): Vuelo =
        vueloRepository.findById(id).orElseThrow { NotFoundException("Vuelo $id no encontrado") }

    fun create(dto: VueloCreateDto): VueloDto {
        val entity = dto.toEntity()
        return vueloRepository.save(entity).toDto()
    }

    fun update(id: Long, dto: VueloCreateDto): VueloDto {
        if (!vueloRepository.existsById(id)) {
            throw NotFoundException("Vuelo $id no encontrado")
        }
        val entity = dto.toEntity()
        entity.id = id
        return vueloRepository.save(entity).toDto()
    }

    fun delete(id: Long) {
        if (!vueloRepository.existsById(id)) {
            throw NotFoundException("Vuelo $id no encontrado")
        }
        vueloRepository.deleteById(id)
    }

    private fun Vuelo.toDto() = VueloDto(
        id = id!!,
        rutaId = ruta.id!!,
        avionId = avion.id!!,
        salidaTs = salidaTs ?: LocalDateTime.MIN,
        llegadaTs = llegadaTs ?: LocalDateTime.MIN,
        estado = estado ?: ""
    )

    private fun VueloCreateDto.toEntity() = Vuelo(
        ruta = rutaService.findEntityById(rutaId),
        avion = avionService.findEntityById(avionId),
        salidaTs = salidaTs,
        llegadaTs = llegadaTs,
        estado = estado
    )
}
