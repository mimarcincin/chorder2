package sk.mmarcincin.chorder2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.mmarcincin.chorder2.models.Member;
import sk.mmarcincin.chorder2.services.MemberService;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/findAllMembers")
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }


    @GetMapping(value = "/findMemberById/{memberId}")
    public Member getMemberById(@PathVariable String memberId) {
        return memberService.findMemberById(memberId);
    }

    @PostMapping(value = "/addMember")
    public void addMember(@RequestBody Member member){
        memberService.addMember(member);
    }

    @PutMapping(value = "/updateMember/{memberId}")
    public void updateMember(@RequestBody Member member, @PathVariable String memberId) {
        memberService.updateMember(memberId, member);
    }

    @PutMapping(value = "/addAchievedTask/{memberId}/{taskId}")
    public void addAchievedTask(@PathVariable String memberId, @PathVariable String taskId){
        memberService.addAchievedTask(memberId,taskId);
    }

    @DeleteMapping(value =  "/deleteMember/{memberId}")
    public void deleteMember(@PathVariable String memberId) {
        memberService.deleteMemberById(memberId);
    }

    @GetMapping
    public String home(){
        return  "<h1>API for members</h1><br>"
                +"<p>List of apis:<br>"
                +"GET /findAllMembers<br>"
                +"GET /findMemberById/{memberId}<br>"
                +"POST /addMember + body<br>"
                +"PUT /updateMember/{memberId} + body<br>"
                +"PUT /addAchievedTask/{memberId}/{taskId}<br>"
                +"DELETE /deleteMember/{memberId}<br></p>";
    }

}
