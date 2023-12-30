package com.mytutor.server.Repository;

import com.mytutor.server.Models.Attendance;
import com.mytutor.server.Models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByWeekNumberAndSlotDayOfWeekAndPresentAndSlot_Course_Id(Integer weekNumber, String dayOfWeek, boolean present, String courseId);

    Optional<Attendance> findBySlot_SlotIdAndTutor_IdAndWeekNumber(int slotId, String tutorId,int weekNumber);
}
