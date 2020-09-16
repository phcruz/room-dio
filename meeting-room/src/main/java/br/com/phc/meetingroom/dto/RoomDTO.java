package br.com.phc.meetingroom.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String date;
	private String startHour;
	private String endHour;
}
