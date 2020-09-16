package br.com.phc.meetingroom.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.meetingroom.dto.RoomDTO;
import br.com.phc.meetingroom.exception.NotFoundException;
import br.com.phc.meetingroom.mapper.RoomMapper;
import br.com.phc.meetingroom.model.Room;
import br.com.phc.meetingroom.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomMapper roomMapper;

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	public Room getRoomById(Long id) {
		return roomRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Room not found - id: " + id));
	}

	public Room createRoom(@Valid RoomDTO roomDTO) {
		Room room = roomMapper.toModel(roomDTO);
		return roomRepository.save(room);
	}

	public Room updateRoom(Long id, Room room) {
		Room existsRoom = getRoomById(id);
		existsRoom.setName(room.getName());
		existsRoom.setDate(room.getDate());
		existsRoom.setStartHour(room.getStartHour());
		existsRoom.setEndHour(room.getEndHour());

		return roomRepository.save(existsRoom);
	}

	public void deleteRoom(Long id) {
		Room room = getRoomById(id);
		roomRepository.delete(room);
	}
}
