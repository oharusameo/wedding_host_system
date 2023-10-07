package com.quxue.wedding_host.controller;

import com.quxue.wedding_host.pojo.Host;
import com.quxue.wedding_host.pojo.Result;
import com.quxue.wedding_host.service.HostService;
import com.quxue.wedding_host.util.MinIOUtils;
import com.quxue.wedding_host.vo.HostVo;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/front/host")
public class HostController {
    @Resource
    private HostService hostService;
    @Resource
    private MinIOUtils minIOUtils;
    List<String> fileType = Arrays.asList("jpg", "png");

    @RequestMapping("/batchDelHost.do")
    public Result batchDelHost(@RequestBody List<Integer> hostIds) {
        if (hostService.batchDelHost(hostIds) > 0) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    @RequestMapping("/delHost.do")
    public Result delHost(Integer hid) {
        if (hostService.delHost(hid) == 1) {
            return Result.success("主持人删除成功");
        }
        return Result.error("删除失败");
    }

    @RequestMapping("/editHost.do")
    public Result editHost(@RequestBody Host host) {
        if (hostService.editHost(host) == 1) {
            return Result.success("更新主持人信息成功");
        }
        return Result.error("更新主持人信息失败");
    }


    /**
     * 后台管理用查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/viewAllHost.do")
    public Result viewHostByAdmin(@RequestParam(defaultValue = "1") Integer currentPage,
                                  @RequestParam(defaultValue = "6") Integer pageSize) {
        return Result.success(hostService.selectHostsByAdmin(currentPage, pageSize));
    }

    /**
     * 后台分页用查询
     *
     * @param host
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/viewHostByCondition.do")
    public Result viewHostByCondition(@RequestBody Host host, @RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "6") Integer pageSize) {
        System.out.println("host = " + host);
        return Result.success(hostService.selectHostsByCondition(host, currentPage, pageSize));
    }

    /**
     * 筛选列表用查询
     *
     * @param low
     * @param high
     * @param sort
     * @return
     */
    @RequestMapping("/viewHosts.do")
    public Result viewHost(@RequestParam(defaultValue = "0") Integer low,
                           @RequestParam(defaultValue = "0") Integer high,
                           @RequestParam(defaultValue = "0") Integer sort) {
        return Result.success(hostService.selectHosts(low, high, sort));
    }

    /**
     * 筛选列表用模糊查询
     *
     * @param hname
     * @return
     */
    @RequestMapping("/viewHostByConditionForFront.do")
    public Result viewHostByCondition(String hname) {
        if (hname.length() == 0) {
            hname = null;
        }
        return Result.success(hostService.selectHostsByCondition(hname));
    }


    @RequestMapping("/viewHostByHid.do")
    public Result viewHostByHid(Integer hid) {
        System.out.println("hid = " + hid);
        return Result.success(hostService.selectHostByHid(hid));
    }


    @RequestMapping("/updateAvatar.do")
    public Result updateAvatar(MultipartFile file, @RequestBody Host host) {
        if (host != null) {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (fileType.contains(extension)) {
                String filePath = minIOUtils.uploadFile(file);
                host.setImage(filePath);
                hostService.updateAvatar(host);
                return Result.success("更新头像成功");
            }
            return Result.error("文件格式错误");
        }
        return Result.error("更新头像失败");
    }


    @RequestMapping("/register.do")
    public Result register(@RequestBody Host host) {//注册时没上传头像
        System.out.println("host = " + host);
        if (host != null) {
            if (hostService.register(host) == 1) {
                return Result.success("注册成功");
            }
        }
        return Result.error("注册失败");
    }

    @RequestMapping("/uploadAvatar.do")
    public Result uploadAvatar(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        System.out.println("extension = " + extension);
        if (fileType.contains(extension)) {
            String filePath = minIOUtils.uploadFile(file);
            if (filePath != null) {
                System.out.println("filePath = " + filePath);
                return Result.success(filePath);
            }
        }
        return null;
    }


}
