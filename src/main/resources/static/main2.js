/* global memory,util
*/
function dynamicallyLoadScript(url) {
    var script = document.createElement("script");  // create a script DOM node
    script.src = url;  // set its src to the provided URL

    document.head.appendChild(script);  // add it to the end of the head section of the page (could change 'head' to 'body' to add it to the end of the body section instead)
}
dynamicallyLoadScript("/assets/js/util.js");
dynamicallyLoadScript("/assets/js/memory.js");
console.log("Main2");

var tabs = document.getElementsByClassName("js-cd-tabs"),
  tabsArray = [],
  resizing = false;
  console.log(tabs.length);
if( tabs.length > 0 ) {
  for( var i = 0; i < tabs.length; i++) {
    (function(i){
      tabsArray.push(new TabbedNavigation(tabs[i]));
    })(i);
  }


  window.addEventListener("resize", function(event) {
    if( !resizing ) {
      resizing = true;
      (!window.requestAnimationFrame) ? setTimeout(checkTabs, 250) : window.requestAnimationFrame(checkTabs);
    }
  });
}

function init(self) {
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
}

function TabbedNavigation( element ) {
  this.element = element;
  this.navigation = this.element.getElementsByClassName("cd-tabs__navigation")[0];
  this.navigationElements = this.navigation.getElementsByClassName("cd-tabs__list")[0];
  memory.setTNE(document.getElementsByClassName("cd-tabs__list"));
  this.content = this.element.getElementsByClassName("cd-tabs__panels")[0];
  this.activeTab;
  this.activeContent;
  init(this);
  console.log("Funkab");
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

function checkTabs() {
  tabsArray.forEach(function(tab){
    tab.toggleNavShadow();
  });
  resizing = false;
};
