package br.com.phc.meetingroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.meetingroom.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}
