/* global memory,util */


$(document).ready(function(){

    $("#workhour-form").submit(function(event){
        if($.trim($("#notes").val()) === ""){
            $("#notes").val("No notes");
        }
    });
    $("#company-form").submit(function(event){
        if($.trim($("#company_name").val()) === ""){
            $("#company-registration-validation-error").show();
            return false;
        }
    });

    function TabbedNavigation( element ) {
      this.element = element;
      this.navigation = this.element.getElementsByClassName("cd-tabs__navigation")[0];
      this.navigationElements = this.navigation.getElementsByClassName("cd-tabs__list")[0];
      memory.setTNE(document.getElementsByClassName("cd-tabs__list"));
      this.content = this.element.getElementsByClassName("cd-tabs__panels")[0];
      this.activeTab;
      this.activeContent;
      this.init();
      console.log("Funkab");
    };

    TabbedNavigation.prototype.init = function() {
      var self = this;
      memory.loadItem();
      var selectedItem;// = event.target.closest('.cd-tabs__item');
      if(memory.hasMemory()){
        selectedItem = memory.selectedTab;
      }
      else{
        selectedItem = event.target.closest('.cd-tabs__item');
      }

      self.activeTab = selectedItem;
      console.log("Get active tab: ",self.activeTab);
      self.activeContent = document.getElementById();
      //self.activeContent = $( ".cd-tabs__item" ).attr("href").replace('#', '');
      self.updateContent();
      //listen for the click on the tabs navigation
      this.navigation.addEventListener("click", function(event){
        event.preventDefault();

        selectedItem = event.target.closest('.cd-tabs__item');

        if(selectedItem && !Util.hasClass(selectedItem, "cd-tabs__item--selected")) {

          self.activeTab = selectedItem;
          self.activeContent = document.getElementById(self.activeTab.getAttribute("href").replace('#', ''));
          //self.activeContent = $( ".cd-tabs__item" ).attr("href").replace('#', '');
          self.updateContent();
        }
        memory.saveItem(selectedItem);
      });

      //listen for the scroll in the tabs navigation
      this.navigationElements.addEventListener('scroll', function(event){
        self.toggleNavShadow();
      });
    };

    TabbedNavigation.prototype.updateContent = function() {
      var self = this;
      var actualHeight = this.content.offsetHeight;
      //update navigation classes
      Util.removeClass(this.navigation.querySelectorAll(".cd-tabs__item--selected")[0], "cd-tabs__item--selected");
      Util.addClass(this.activeTab, "cd-tabs__item--selected");
      //update content classes
      Util.removeClass(this.content.querySelectorAll(".cd-tabs__panel--selected")[0], "cd-tabs__panel--selected");
      Util.addClass(this.activeContent, "cd-tabs__panel--selected");
      //set new height for the content wrapper
      if(window.requestAnimationFrame && window.getComputedStyle(this.element).getPropertyValue('display') == 'block') {
        Util.setHeight(actualHeight, this.activeContent.offsetHeight, this.content, 200, function(){
          self.content.removeAttribute('style');
        });
      }
    };

    TabbedNavigation.prototype.toggleNavShadow = function() {
      //show/hide tabs navigation gradient layer
      this.content.removeAttribute("style");
      var navItems = this.navigationElements.getElementsByClassName("cd-tabs__item"),
        navigationRight = Math.floor(this.navigationElements.getBoundingClientRect().right),
        lastItemRight = Math.ceil(navItems[navItems.length - 1].getBoundingClientRect().right);
      ( navigationRight >= lastItemRight )
        ? Util.addClass(this.element, "cd-tabs--scroll-ended")
        : Util.removeClass(this.element, "cd-tabs--scroll-ended");
    };

    var tabs = document.getElementsByClassName("js-cd-tabs"),
      tabsArray = [],
      resizing = false;
    if( tabs.length > 0 ) {
      for( var i = 0; i < tabs.length; i++) {
        (function(i){
          tabsArray.push(new TabbedNavigation(tabs[i]));
        })(i);
      }

      console.log(tabsArray);

      window.addEventListener("resize", function(event) {
        if( !resizing ) {
          resizing = true;
          (!window.requestAnimationFrame) ? setTimeout(checkTabs, 250) : window.requestAnimationFrame(checkTabs);
        }
      });
    }

    function checkTabs() {
      tabsArray.forEach(function(tab){
        tab.toggleNavShadow();
      });
      resizing = false;
    };
    //})();
    console.log("");
    console.log("Test2");
    /*
    $("#tab-1").click(function() {
      window.localStorage.setItem("tab", "tab-1");
      window.localStorage.setItem("content", "tab-dashboard");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-2").click(function() {
      // Store
      window.localStorage.setItem("tab", "tab-2");
      window.localStorage.setItem("content", "tab-workhours");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-3").click(function() {
      // Store
      window.localStorage.setItem("tab", "tab-3");
      window.localStorage.setItem("content", "tab-my-profile");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-4").click(function() {
      window.localStorage.setItem("tab", "tab-4");
      window.localStorage.setItem("content", "tab-admin");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });
    $("#tab-5").click(function() {
      window.localStorage.setItem("tab", "tab-5");
      window.localStorage.setItem("content", "tab-company");
      //$( ".cd-tabs__list > li > a" ).removeClass("cd-tabs__item");
      $( ".cd-tabs__panel" ).removeClass("cd-tabs__panel");
    });


    function defaultTab(){
        var defaultTabName = document.getElementById("tab-1");
    }

    function getTab(){
        var item = window.localStorage.getItem("tab");
        console.log(item);
        return item;
    }

    function getContent(){
        var item = window.localStorage.getItem("content");
        console.log(item);
        return item;
    }

    function activeTab(){
        console.log(getTab());
        if(false){
            defaultTab();
        }else{
            //var activatedTabName = document.getElementById("" + getTab())
            $( "#" + getTab() ).addClass( "cd-tabs__item--selected" );
            $( "#" + getContent() ).addClass( "cd-tabs__panel--selected" );
            window.localStorage.removeItem("content");
            window.localStorage.removeItem("tab");
        }
    }

    activeTab();
    */
});

//(function(){
  // Responsive Tabbed Navigation - by CodyHouse.co