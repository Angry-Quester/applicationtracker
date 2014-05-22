function FormBuilder (){

}


FormBuilder.prototype.init = function(smartListInitData) {
    console.log("FormBuilder ======================= init( " + smartListInitData.toString() +" )");

    var that = this;

    /*========= global initializations to use in "this" namespace*/
        this.initData = smartListInitData;
        this.$template = this.templateReader(this.initData.templateId);

        var addButtonId = "#" + this.initData.wrapperId + " ." + this.initData.addButtonClass;
        var $addButton = $(addButtonId);
            $($addButton).click(that.addElementEventGenerator());

        this.$addButton = $addButton;

    /*========= global initializations to use in "this" namespace*/
    this.templateReader(this.initData.templateId);
};


FormBuilder.prototype.templateReader = function(templateId) {

    console.log("FormBuilder ======================= templateReader( " + templateId +" )");

        //read template and detach it from the page
        return $("#" + templateId).detach();
};


FormBuilder.prototype.addListElementEvent = function(elementNumber) {
    console.log("FormBuilder ======================= addListElement( " + elementNumber +" )");

    //local copy of the global template
    var $template = this.$template;

    //if new element number hadn't been supplied calculate it
    if (!elementNumber) {
        elementNumber = this.getListLength();
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

FormBuilder.prototype.deleteElementEventGenerator = function(elementId) {
    var that = this;

    return function() {
        console.log("FormBuilder ======================= deleteElementEvent( " + elementId +" )");
        //  remove element and save it's index for
        //  further processing
        var $elementToDelete = $("#" + elementId);
            var elementIndex = $elementToDelete.index();
        $elementToDelete.remove();

        // fire re-indexation by the supplied index
        that.reindexator(elementIndex);
    };
};

FormBuilder.prototype.buildListElement = function($element, $template, elementNumber) {
    console.log("FormBuilder ======================= buildListElement( $element, $template, " + elementNumber +" )");

    //Create correct listElement id and use it as a delete button hook
    var templateDivId = this.listDivIdGenerator(this.initData.parentWrapperId, this.initData.wrapperId, elementNumber);

    $($element).attr("id", templateDivId);

    /*========== "-" button ==========*/
    // find delete button for this element
        //Remove all events from the "-" button
        //bind delete event to the button "-"
    var $deleteButton = $($element).find("." + this.initData.deleteButtonClass);
        $($deleteButton).off();
        $($deleteButton).click(this.deleteElementEventGenerator(templateDivId));

    /*========== controls ==========*/
    //get all inputs of the new element
    //get id and name for the current element from the template
    var $elementControls = $($element).children();
    var $templateControls = $($template).children();

    // look through inputs and apply changes to id's and name's
    // and maybe do some additional job
    for (var i=0; i<$elementControls.length; i++) {
        //determine weather the element is a "control" or not
        var flag = $($elementControls[i]).hasClass(this.initData.formControlClass);
        if (flag) {
            console.log("======= applying new names to :: " + $elementControls[i].id + " :: " + $elementControls[i].name);

            //initial id and name from the template
            var idValue = $templateControls[i].id;
            var nameValue = $templateControls[i].name;

            //here i generate full names for every field
            var generatedId = this.listControlIdGenerator(this.initData.parentWrapperId,
                                                          this.initData.wrapperId,
                                                          idValue,
                                                          elementNumber);
            $($elementControls[i]).attr("id", generatedId);

            var generatedName = this.listControlNameGenerator(this.initData.parentWrapperId,
                                                              this.initData.wrapperId,
                                                              nameValue,
                                                              elementNumber);
            $($elementControls[i]).attr("name", generatedName);
        }
    }
    //return prepared element of whatever
    return $element;
};


FormBuilder.prototype.reindexator = function(elementIndex) {
    console.log("FormBuilder ======================= reindexator( " + elementIndex +" )");
    //if no elements left - return
    var elementsCount = this.getListLength();
    if (elementsCount) {
        //get an element at the index mark
        var $listElementDiv = $("#" + this.initData.wrapperId).children().eq(elementIndex);

        //go through all the elements lower than this element and do re-indexation
        for (var i=elementIndex; i<elementsCount; i++) {
            // reindex the element
            // and jump on the next one
            this.buildListElement($listElementDiv, this.$template, i);

            $listElementDiv = $($listElementDiv).next();
        }
    }
};


FormBuilder.prototype.getListLength = function() {
    //-1 because of the addButton
    return $("#" + this.initData.wrapperId).children().length-1;
};

FormBuilder.prototype.listDivIdGenerator = function(parentWrapperId, wrapperId, elementNumber) {
    var result = parentWrapperId !== null ? (parentWrapperId + "_") : "";
        result += wrapperId + "_" + elementNumber;
    return result;
};

FormBuilder.prototype.listControlIdGenerator = function(parentWrapperId, wrapperId, inputId, elementNumber) {
    var result = parentWrapperId !== null ? (parentWrapperId + ".") : "";
        result += wrapperId + elementNumber + "." + inputId;
    return result;
};

FormBuilder.prototype.listControlNameGenerator = function(parentWrapperId, wrapperId, inputName, elementNumber) {
    var result = parentWrapperId !== null ? (parentWrapperId + ".") : "";
        result += wrapperId + "[" + elementNumber + "]" + "." + inputName;
    return result;
};