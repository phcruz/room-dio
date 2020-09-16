package br.com.phc.meetingroom.mapper;

import org.springframework.stereotype.Service;

import br.com.phc.meetingroom.dto.RoomDTO;
import br.com.phc.meetingroom.model.Room;

@Service
public class RoomMapper {
	
	public Room toModel(RoomDTO roomDTO) {
		return new Room(0L, roomDTO.getName(), roomDTO.getDate(), roomDTO.getStartHour(), roomDTO.getEndHour());
	}

	public RoomDTO toDTO(Room room) {
		return new RoomDTO(room.getName(), room.getDate(), room.getStartHour(), room.getEndHour());
	}
}
