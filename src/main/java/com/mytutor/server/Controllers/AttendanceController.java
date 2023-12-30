package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Attendance;
//import com.mytutor.server.Repository.AttendanceRepository;
import com.mytutor.server.Models.AttendanceData;
import com.mytutor.server.Models.Slot;
import com.mytutor.server.Models.Tutor;
import com.mytutor.server.Repository.AttendanceRepository;
import com.mytutor.server.Repository.SlotRepository;
import com.mytutor.server.Repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    TutorRepository tutorRepository;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody List<AttendanceData> attendanceDataList) {
        try {
            // Implement logic to mark attendance based on the received data
            for (AttendanceData data : attendanceDataList) {
                String tutorId = data.getTutorId();
                int slotId = data.getSlotId();
                boolean isPresent = data.getPresent();
                int weekNumber = data.getWeekNumber();

                Tutor tutor = tutorRepository.findById(tutorId).get();
                Slot slot = slotRepository.findById(slotId).get();

                Optional<Attendance> similarAttendance = attendanceRepository.findBySlot_SlotIdAndTutor_IdAndWeekNumber(slotId, tutorId,weekNumber);
                Attendance attendance;
                if (similarAttendance.isEmpty()) {
                    attendance = new Attendance();
                    attendance.setPresent(isPresent);
                    attendance.setTutor(tutor);
                    attendance.setSlot(slot);
                    attendance.setWeekNumber(weekNumber);
                }else{
                    attendance = similarAttendance.get();
                    attendance.setPresent(isPresent);
                }

                attendanceRepository.save(attendance);
            }

            return ResponseEntity.ok("Attendance marked successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error marking attendance.");
        }
    }

    @GetMapping("/present")
    public ResponseEntity<List<Attendance>> getPresent(@RequestParam Integer weekNumber, @RequestParam String dayOfWeek,@RequestParam String course) {
        return ResponseEntity.ok(attendanceRepository.findAllByWeekNumberAndSlotDayOfWeekAndPresentAndSlot_Course_Id(weekNumber,dayOfWeek,true,course));
    }
}
