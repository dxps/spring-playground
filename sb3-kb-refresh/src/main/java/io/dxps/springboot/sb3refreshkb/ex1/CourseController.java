package io.dxps.springboot.sb3refreshkb.ex1;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @GetMapping("/courses")
    List<Course> getCourses() {
        return Arrays.asList(
                new Course("1", "First course", "1st course's description"),
                new Course("2", "Second course", "2nd course's description"),
                new Course("3", "Third course", "3rd course's description"));
    }

}
