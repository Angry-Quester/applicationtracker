/*
* TODO
* (DONE) 1. add smartList elements internal ordering
* (DONE) 2. make smart list element independent from any markup through $($domContainer).find("." + smartListElement)
* (DONE) 3. add object cloner for initData
* 4. Add events handlers and "smart list manager" wrapper class
* */


function SmartList (){

}


SmartList.prototype.init = function(smartListInitData) {
    console.log("SmartList ======================= init( " + smartListInitData.toString() +" )");

    /*========= global initializations to use in "this" namespace*/
    this.initData = smartListInitData; //get all properties from initialiser

    //look for a domContainer
    //if not found - make one
    //this should only work for the root smartList
    this.$domContainer = (!this.initData.$domContainer) ?
                         $("#" + this.initData.sListName) :
                         this.initData.$domContainer;

    //apply parent id path to the container if necessary
    var sListInitId = this.sLisIdGenerator(this.initData.parentIdPath, this.initData.sListName);
        $(this.$domContainer).attr("id", sListInitId);


    //look for templateDomContainer
    this.$templateDomContainer = (!this.initData.$templateDomContainer) ?
                                 $("#" + this.initData.sListTemplateContainerId).detach() : //!!! detaches container!!!
                                 this.$templateDomContainer = this.initData.$templateDomContainer;

    //read list element template
    this.$elementTemplate = this.templateReader(this.initData.sListTemplateElementId);

    //find and init add "+" button
    var $addButton = $(this.$domContainer).find("." + this.initData.addButtonClass);

    if ($addButton.length === 0) {
        $addButton = this.addButtonReader(this.initData.addButtonClass);
        $(this.$domContainer).append($addButton);
    }

    $($addButton).off();
    $($addButton).click(this.addElementEventGenerator());
    this.$addButton = $addButton;

    //fire reindexation. Having no elements in the sList, it will fail on the first loop,
    this.elementReindexator(0);

    /*========= global initializations to use in "this" namespace*/
};


SmartList.prototype.templateReader = function(templateElementId) {
    console.log("SmartList ======================= templateReader( " + templateElementId +" )");

        var $templateDomContainer = this.$templateDomContainer;

        var $template = $($templateDomContainer).find("#" + templateElementId);

        //read template and detach it from the container
        return $($template).detach();
};

SmartList.prototype.addButtonReader = function(addButtonClass) {
    console.log("SmartList ======================= addButtonReader( " + addButtonClass +" )");

    var $templateDomContainer = this.$templateDomContainer;
    var $addButton = $($templateDomContainer).find("." + addButtonClass);

    //read the button and clone it
    return $($addButton).clone();
};


SmartList.prototype.addListElementEvent = function(elementNumber) {
    console.log("SmartList ======================= addListElement( " + elementNumber +" )");

    //local copy of the global template
    var $template = this.$elementTemplate;

    //if new element number hadn't been supplied calculate it
    if (!elementNumber) {
        elementNumber = this.getSListElementsCount();
    }

    //clone stored template to use it again if necessary
    var $clonedTemplateElement = $($template).clone();

    //build new DOM element for the list with an appropriate number
    var $element = this.buildListElement($clonedTemplateElement, $template, elementNumber);

    //attach element to the list <div>
    //before add button
    $(this.$addButton).before($element);
};

SmartList.prototype.addElementEventGenerator = function() {
    var that = this;

    return function() {
        console.log("SmartList ======================= addElementEvent( )");

        // fire add element event
        that.addListElementEvent();
    };
};


SmartList.prototype.deleteElementEvent = function(elementId) {
    console.log("SmartList ======================= deleteElementEvent( " + elementId +" )");
    //  remove element and save it's index for
    //  further processing

        //*** jQuery requires escaped "." and "," symbols
        //var $elementToDelete = $("#" + elementId);
    var $elementToDelete = document.getElementById(elementId);

        //*** jQuery requires escaped "." and "," symbols
        // var elementIndex = $elementToDelete.index();
    var elementIndex = $($elementToDelete).index();

    $elementToDelete.remove();

    //remove an indexed element from smart list elements array
    if (this.element) {
        var elementsArray = this.element;
        elementsArray.splice(elementIndex, 1);
    }

    // fire re-indexation by the supplied index
    this.elementReindexator(elementIndex);
};



SmartList.prototype.deleteElementEventGenerator = function(elementId) {
    var that = this;

    return function() {
        console.log("SmartList ======================= deleteElementEventGenerator( " + elementId +" )");
        // fire delete element event
        that.deleteElementEvent(elementId);
    };
};

SmartList.prototype.buildListElement = function($element, $template, elementNumber) {
    console.log("SmartList ======================= buildListElement( $element, $elementTemplate, " + elementNumber +" )");

    //Create correct listElement id to use in reindexation and as a delete button hook
    var templateDivId = this.sLisElementIdGenerator(this.initData.parentIdPath, this.initData.sListName, elementNumber);
    //apply new id to the element
    $($element).attr("id", templateDivId);

    /*========== "-" button ==========*/
    //get delete "-" button
    //Remove all events from the "-" button
    //Bind delete event to the delete "-" button
    //find "-" button and
    var $deleteButton = $($element).find("." + this.initData.deleteButtonClass);
    $($deleteButton).off();
    $($deleteButton).click(this.deleteElementEventGenerator(templateDivId));

    //get all inputs of the new element and of the template
    //to get id and name for the current element from the template
    var $elementControls = this.getControls($element);
    var $templateControls = this.getControls($template);

    //elements array
    if (!this.elements) {
        this.elements = [];
    }

    if (!this.elements[elementNumber]){
        this.elements[elementNumber] = {};
    }

    /*========== controls ==========*/
    // look through inputs and apply changes to id's and name's
    // and maybe do some additional job
    for (var i=0; i<$elementControls.length; i++) {

        //controls array for this element
        if (!this.elements[elementNumber].controls) {
            this.elements[elementNumber].controls = [];
        }

        if (!this.elements[elementNumber].controls[i]) {
            this.elements[elementNumber].controls[i] = null;
        }

        //determine weather the element is a "control" or not
        var isControl = $($elementControls[i]).hasClass(this.initData.controlClass);
        if (isControl) {
            console.log("======= 1 applying new names to :: " + $elementControls[i].id + " :: " + $elementControls[i].name);

            //initial id and name from the template
            var idValue = $templateControls[i].id;
            var nameValue = $templateControls[i].name;

            //here i generate full ids and names for every smart list control
            var generatedId = this.sListControlIdGenerator(this.initData.parentIdPath, this.initData.sListName, idValue, elementNumber);
            $($elementControls[i]).attr("id", generatedId);

            var generatedName = this.sListControlNameGenerator(this.initData.parentNamePath, this.initData.sListName, nameValue, elementNumber);
            $($elementControls[i]).attr("name", generatedName);
        }

        //determine weather the element is a "smartList" or not
        var isSmartList = $($elementControls[i]).hasClass(this.initData.smartListClass);
        if (isSmartList ) {
            console.log("======= 2 applying new names to :: " + $elementControls[i].id + " :: " + $elementControls[i].name);

            var smartListInitData = this.clone(this.initData.controls[i][this.initData.smartListClass]);

                smartListInitData.parentIdPath = this.sLisElementIdGenerator(this.initData.parentIdPath, this.initData.sListName, elementNumber);
                smartListInitData.parentNamePath = this.sLisElementNameGenerator(this.initData.parentNamePath, this.initData.sListName, elementNumber);

                smartListInitData.$domContainer = $elementControls[i];
                smartListInitData.$templateDomContainer = $($templateControls[i]).clone();

                //saw off element body from the template if there is one
                $($elementControls[i]).find("#" + smartListInitData.sListTemplateElementId).detach();

            var smartList = this.elements[elementNumber].controls[i];

            if (smartList) {
                smartList = this.elements[elementNumber].controls[i];
                smartList.init(smartListInitData); //reindex all id's and name's
            } else {
                smartList = new SmartList();         //create form builder and
                smartList.init(smartListInitData); //reindex all id's and name's
                this.elements[elementNumber].controls[i] = smartList;
            }
        }
    }
    //return prepared element of whatever
    return $element;
};


SmartList.prototype.elementReindexator = function(elementIndex) {
    console.log("SmartList ======================= elementReindexator( " + elementIndex +" )");
    //if no elements left - return
    var elementsCount = this.getSListElementsCount();
    if (elementsCount > 0) {
        //get an element at the index mark
        var $listElementDiv = $(this.$domContainer).children().eq(elementIndex);

        //go through all the elements lower than this element and do re-indexation
        for (var i=elementIndex; i<elementsCount; i++) {
            // reindex the element
            // and jump on the next one
            this.buildListElement($listElementDiv, this.$elementTemplate, i);

            $listElementDiv = $($listElementDiv).next();
        }
    }
};

SmartList.prototype.getControls = function($element) {
    var controlsArray = [];
    var controlClass = this.initData.controlClass;

    var traverser = function($node) {
        var $children = $($node).children();
        var childrenLength= $children.length;

        for (var i=0; i<childrenLength; i++) {
            if ($($children[i]).hasClass(controlClass)) {
                controlsArray.push($children[i]);
            } else {
                traverser($children[i]);
            }
        }
    };

    traverser($element);

    return controlsArray;
};

SmartList.prototype.getSListElementsCount = function() {
    //-1 because of the addButton
    return $(this.$domContainer).children().length-1;
};

SmartList.prototype.sLisIdGenerator = function(parentIdPath, sListId) {
    var result = parentIdPath !== null ? (parentIdPath + ".") : "";
    result += sListId;
    return result;
};

SmartList.prototype.sLisElementIdGenerator = function(parentIdPath, sListName, elementNumber) {
    var result = parentIdPath !== null ? (parentIdPath + ".") : "";
        result += sListName + "" + elementNumber;
    return result;
};

SmartList.prototype.sLisElementNameGenerator = function(parentNamePath, sListName, elementNumber) {
    var result = parentNamePath !== null ? (parentNamePath + ".") : "";
        result += sListName + "[" + elementNumber + "]";
    return result;
};

SmartList.prototype.sListControlIdGenerator = function(parentIdPath, sListName, controlId, elementNumber) {
    var result = parentIdPath !== null ? (parentIdPath + ".") : "";
        result += sListName + "" + elementNumber + "." + controlId;
    return result;
};

SmartList.prototype.sListControlNameGenerator = function(parentNamePath, sListName, controlName, elementNumber) {
    var result = parentNamePath !== null ? (parentNamePath + ".") : "";
        result += sListName + "[" + elementNumber + "]" + "." + controlName;
    return result;
};

SmartList.prototype.clone = function(data) {
    var result = {};
    for (var p in data) {
        if (data.hasOwnProperty(p)) {
            result[p] = data[p];
        }
    }
    return result;
};