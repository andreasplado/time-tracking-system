var memory = {
    tabChosen: {},
    tabHREF: "",
    tne: [],
    selectedTab: {}
};

var store = window.localStorage; 

memory.saveItem = function (selectedItem) {
    console.log("saving:" + selectedItem.href);
    store.setItem("tab", selectedItem.href);
}

memory.loadItem = function () {
    
    var tabChosen = memory.findElementByHREF();
    if(tabChosen){
        Util.removeClass(document.querySelectorAll(".cd-tabs__item--selected")[0], "cd-tabs__item--selected");
        Util.addClass(tabChosen, "cd-tabs__item--selected");
    }    
}

memory.hasMemory = function() {
    if(store.getItem("tab")) {
        console.log("tab exists in memory");
        return true;
    }
    return false;
}

memory.findElementByHREF = function () {
    var tabslist = memory.tne;
    var tabs = document.getElementsByClassName("cd-tabs__item");
    console.log("array length for tabbed navigation elements" + tabslist.length);
    memory.tabHREF = store.getItem("tab");
    console.log("tabhref:" + memory.tabHREF);
    for(let i = 0; i < tabs.length;i++){
        var element = tabs[i];
        console.log(element);
        if(element.href=== memory.tabHREF){
            console.log("element found" + element);
            memory.selectedTab = element;
            return element;
        };
    };
    console.log("found:" + tabs[0] + "and" + memory.tabHREF);
}

memory.setTNE = function(element){
    console.log("setting element" + element);
    memory.tne = element;
}