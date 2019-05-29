package testgroup.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.test.model.Comp;
import testgroup.test.service.CompService;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private int page;
    private String search;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allComps(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String search) {
        this.page = page;
        this.search = search;
        int compsCount = compService.compsCount(search);
        int pagesCount = (compsCount + 9)/10;
        if (page>pagesCount && pagesCount>0)
            this.page = pagesCount;
        else if (pagesCount==0)
            this.page = 1;
        List<Comp> comps = new ArrayList<>();
        comps = compService.allComps(this.page, search);
        int compsReadyCount = compService.compsReadyCount();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("comps");
        modelAndView.addObject("page", this.page);
        modelAndView.addObject("search", search);
        modelAndView.addObject("compsList", comps);
        modelAndView.addObject("compsCount", compsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("compsReadyCount", compsReadyCount);
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchPage(@RequestParam String search) {
        List<Comp> comps = compService.searchComps(search);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        modelAndView.addObject("compsList", comps);
        return modelAndView;
    }

    private CompService compService;

    @Autowired
    public void setCompService(CompService compService) {
        this.compService = compService;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Comp comp = compService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("comp", comp);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editComp(@ModelAttribute("comp") Comp comp) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        compService.edit(comp);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addComp(@ModelAttribute("comp") Comp comp) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        compService.add(comp);
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteComp(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page+"&search="+this.search);
        Comp comp = compService.getById(id);
        compService.delete(comp);
        return modelAndView;
    }





}
