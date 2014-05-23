/*
* TODO
* +1. add smartList elements internal ordering (DONE. Solved this problem by getElements recoursive method)
* +2. make smart list element independent from any markup through $($domContainer).find("." + smartListElement) (DONE)
* 3. add object cloner for initData
* */


function FormBuilder (){

}


FormBuilder.prototype.init = function(smartListInitData) {
    console.log("FormBuilder ======================= init( " + smartListInitData.toString() +" )");

    /*========= global initializations to use in "this" namespace*/
        this.initData = smartListInitData; //get all properties from initialiser

        //look for domContainer
        //if not found - make one
        //this should only work for the root smartList
        if (!this.initData.$domContainer) {
            this.$domContainer = $("#" + this.initData.sListName)
        } else {
            this.$domContainer = this.initData.$domContainer;
        }

        //apply parent id path to the container necessary
        var sListInitId = this.sLisIdGenerator(this.initData.parentIdPath, this.initData.sListName);
        $(this.$domContainer).attr("id", sListInitId);

        //read list element template
        this.$elementTemplate = this.templateReader(this.initData.sListTemplateId);

        //find and init add "+" button
        this.$addButton = $(this.$domContainer).find("." + this.initData.addButtonClass);
        $(this.$addButton).click(this.addElementEventGenerator());

        //fire reindexation. Having no elements in the sList, it will fail on the first loop,

    /*========= global initializations to use in "this" namespace*/

};


FormBuilder.prototype.templateReader = function(elementTemplateId) {
    console.log("FormBuilder ======================= templateReader( " + elementTemplateId +" )");

        var $domContainer = this.$domContainer;

        var $template = $($domContainer).find("#" + elementTemplateId);

        //read template and detach it from the page
        return $($template).detach();
};


FormBuilder.prototype.addListElementEvent = function(elementNumber) {
    console.log("FormBuilder ======================= addListElement( " + elementNumber +" )");

    console.log($("#publications_0.publishers").length);
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

FormBuilder.prototype.addElementEventGenerator = function() {
    var that = this;

    return function() {
        console.log("FormBuilder ======================= addElementEvent( )");

        // fire add element event
        that.addListElementEvent();
    };
};

FormBuilder.prototype.deleteElementEvent = function(elementId) {
    console.log("FormBuilder ======================= deleteElement( " + elementId +" )");
    //  remove element and save it's index for
    //  further processing
    var $elementToDelete = $("#" + elementId);
    var elementIndex = $elementToDelete.index();
    $elementToDelete.remove();

    // fire re-indexation by the supplied index
    this.elementReindexator(elementIndex);
};

FormBuilder.prototype.deleteElementEventGenerator = function(elementId) {
    var that = this;

    return function() {
        console.log("FormBuilder ======================= deleteElementEvent( " + elementId +" )");
        that.deleteElementEvent(elementId);
    };
};

FormBuilder.prototype.buildListElement = function($element, $template, elementNumber) {
    console.log("FormBuilder ======================= buildListElement(" + $element.toString() + ", " + $template.toString() + ", " + elementNumber +" )");

    //Create correct listElement id to use in reindexation and as a delete button hook
    var templateDivId = this.sLisElementIdGenerator(this.initData.parentIdPath, this.initData.sListName, elementNumber);
    //apply new id to the element
    $($element).attr("id", templateDivId);

    /*========== "-" button for this smartListElement ==========*/
    //get delete "-" button
    //Remove all events from the "-" button
    //Bind delete event to the delete "-" button
    var $deleteButton = this.getDeleteButton($element);
    $($deleteButton).off();
    $($deleteButton).click(this.deleteElementEventGenerator(templateDivId));


    /*========== controls ==========*/
    //get all сщтекщды of the new smartList element
    var $elementControls = this.getControls($element);
    var $templateControls = this.getControls($template);

    // look through inputs and apply changes to id's and name's
    // and maybe do some additional job
    for (var i=0; i<$elementControls.length; i++) {
        //determine weather the element is a "control" or not
        var isControl = $($elementControls[i]).hasClass(this.initData.controlClass);
        if (isControl) {
            console.log("======= applying new names to :: " + $elementControls[i].id + " :: " + $elementControls[i].name);

            //get id and name for the current element from the template
            //if it has one
            var idValue = $templateControls[i].id;
            var nameValue = $templateControls[i].name;

            //here i generate full ids and names for every smart list control
            var generatedId = this.sListControlIdGenerator(this.initData.parentIdPath, this.initData.sListName, idValue, elementNumber);
            $($elementControls[i]).attr("id", generatedId);

            var generatedName = this.sListControlNameGenerator(this.initData.parentNamePath, this.initData.sListName, nameValue, elementNumber);
            $($elementControls[i]).attr("name", generatedName);
        };

        //determine weather the element is a "smartList" or not
        var isSmartList = $($elementControls[i]).hasClass(this.initData.smartListClassClass);
        if (isSmartList ) {
            console.log("======= applying new names to :: " + $elementControls[i].id + " :: " + $elementControls[i].name);

            var smartListInitData = {};

                smartListInitData["parentIdPath"] = this.initData.controls[0]["smart-list"]["parentIdPath"];
                smartListInitData["parentNamePath"] = this.initData.controls[0]["smart-list"]["parentNamePath"];
                smartListInitData["sListIdPath"] = this.initData.controls[0]["smart-list"]["sListIdPath"];
                smartListInitData["sListName"] = this.initData.controls[0]["smart-list"]["sListName"];
                smartListInitData["sListTemplateId"] = this.initData.controls[0]["smart-list"]["sListTemplateId"];
                smartListInitData["sListElementClass"] = this.initData.controls[0]["smart-list"]["sListElementClass"];
                smartListInitData["controlClass"] = this.initData.controls[0]["smart-list"]["controlClass"];
                smartListInitData["smartListClassClass"] = this.initData.controls[0]["smart-list"]["smartListClassClass"];
                smartListInitData["addButtonClass"] = this.initData.controls[0]["smart-list"]["addButtonClass"];
                smartListInitData["deleteButtonClass"] = this.initData.controls[0]["smart-list"]["deleteButtonClass"];

                smartListInitData.parentIdPath = this.sLisElementIdGenerator(this.initData.parentIdPath, this.initData.sListName, elementNumber);
                smartListInitData.parentNamePath = this.sLisElementNameGenerator(this.initData.parentNamePath, this.initData.sListName, elementNumber);
                smartListInitData.$domContainer = $elementControls[i];


            var formBuilder = {};

            if (!this.children) {
                this.children = [];
            }

            if (this.children[elementNumber]) {
                formBuilder = this.children[elementNumber];
                    formBuilder.init(smartListInitData); //reindex all id's and name's
            } else {

                formBuilder = new FormBuilder();         //create form builder and
                    formBuilder.init(smartListInitData); //reindex all id's and name's
                this.children[elementNumber] = [];
                this.children[elementNumber].push(formBuilder);
            }


        }
    }
    //return prepared element of whatever
    return $element;
};


FormBuilder.prototype.elementReindexator = function(elementIndex) {
    console.log("FormBuilder ======================= elementReindexator( " + elementIndex +" )");
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

FormBuilder.prototype.getControls = function($element) {
    var controlsArray = [];
    var controlClass = this.initData.controlClass;

    //recoursive function to traverse through the domContainerTree
    var traverser = function($node) {
        //get all children for the node
        //if there are no children thete is no need to cycle through them
        var $children = $($node).children();
        var childrenLength = $children.length;

        //loop through all the children
        for (var i=0; i<childrenLength; i++) {
            if ( $($children[i]).hasClass(controlClass) ) {
                //if any node has smart list control class push this node into the controlsArray
                controlsArray.push($children[i])
            } else {
                //if this is not a control run traverser on this node
                traverser($children[i]);
            }
        }
    };

    //traverse through element/template DOM tree
    traverser($element);

    return controlsArray;

};

FormBuilder.prototype.getDeleteButton = function($element) {
    return $($element).find("." + this.initData.deleteButtonClass);
};

FormBuilder.prototype.getSListElementsCount = function() {

    //-1 because of the addButton
    return $(this.$domContainer).children().length-1;
};

FormBuilder.prototype.sLisIdGenerator = function(parentIdPath, sListId) {
    var result = parentIdPath !== null ? (parentIdPath + "_") : "";
    result += sListId;
    return result;
};

FormBuilder.prototype.sLisElementIdGenerator = function(parentIdPath, sListName, elementNumber) {
    var result = parentIdPath !== null ? (parentIdPath + "_") : "";
        result += sListName + "_" + elementNumber;
    return result;
};

FormBuilder.prototype.sLisElementNameGenerator = function(parentNamePath, sListName, elementNumber) {
    var result = parentNamePath !== null ? (parentNamePath + ".") : "";
        result += sListName + "[" + elementNumber + "]";
    return result;
};

FormBuilder.prototype.sListControlIdGenerator = function(parentIdPath, sListName, controlId, elementNumber) {
    var result = parentIdPath !== null ? (parentIdPath + "_") : "";
        result += sListName + "_" + elementNumber + "." + controlId;
    return result;
};

FormBuilder.prototype.sListControlNameGenerator = function(parentNamePath, sListName, controlName, elementNumber) {
    var result = parentNamePath !== null ? (parentNamePath + ".") : "";
        result += sListName + "[" + elementNumber + "]" + "." + controlName;
    return result;
};