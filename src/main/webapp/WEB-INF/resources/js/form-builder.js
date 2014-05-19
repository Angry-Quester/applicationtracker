function FormBuilder (){

}

FormBuilder.prototype.init = function(listId, listElementTemplateId, addButtonClass, deleteButtonClass) {
    console.log("FormBuilder ======================= init( " + listId +" )");
    /*========= global initializations to use in "this" namespace*/
        this.listId = listId;
        this.listElementTemplateId = listElementTemplateId;
        this.addButtonClass = addButtonClass;
        this.deleteButtonClass = deleteButtonClass;
    /*========= global initializations to use in "this" namespace*/

    this.templateReader(listElementTemplateId);

};

FormBuilder.prototype.templateReader= function(templateName) {
    console.log("FormBuilder ======================= templateReader( " + templateName +" )");

    /*========= global initializations to use in "this" namespace*/
        //read template and detach it from the page
        this.$templateDiv = $("#" + templateName).detach();
    /*========= global initializations to use in "this" namespace*/
};

FormBuilder.prototype.addListElementEvent = function(elementNumber) {
    console.log("FormBuilder ======================= addListElement( " + elementNumber +" )");

    //local copy of the global template
    var $template = this.$templateDiv;

    //if new element number hadn't been supplied calculate it
    if (!elementNumber) {
        elementNumber = this.getListLength();
    }

    //clone stored template to use it again if necessary
    var $clonedTemplateElement = $($template).clone();

    //build new DOM element for the list with an appropriate number
    var $element = this.buildListElement($clonedTemplateElement, $template, elementNumber);

    //attach element to the list <div>
    $($element).appendTo("#" + this.listId);

};


FormBuilder.prototype.deleteElementEventGenerator = function(elementId) {
    var that = this;

    return function() {
        console.log("FormBuilder ======================= deleteElementEvent( " + elementId +" )");
        /*  remove element and save it's index for
            further processing
        */
        var $elementToDelete = $("#" + elementId);
            var elementIndex = $elementToDelete.index();
        $elementToDelete.remove();

        // fire re-indexation by the supplied index
        that.reindexator(elementIndex);
    };
};

FormBuilder.prototype.buildListElement = function($element, $template, elementNumber) {
    //Create correct listElement id and use it as a delete button hook
    var templateDivId = this.listDivIdGenerator(this.listId, elementNumber);
    $($element).attr("id", templateDivId);

    // find delete button for this element
    var $deleteButton = $($element).find(".delete-button");
        //Remove all events from the "-" button
        $($deleteButton).off();
        //bind delete event to the button "-"
        $($deleteButton).click(this.deleteElementEventGenerator(templateDivId));

    //get all inputs of the new element
    var $elementInputs = $($element).children();

    // look through inputs and apply changes to id's and name's
    // and maybe do some additional job
    for (var i=0; i<$elementInputs.length; i++) {
        console.log("======= applying new names to :: " + $elementInputs[i].id + " :: " + $elementInputs[i].name);

        //get id and name for the current element from the template
        var $templateInputs = $($template).children();
            //id and name i want to change
            var idValue = $templateInputs[i].id;
            var nameValue = $templateInputs[i].name;
            //and change them
            $($elementInputs[i]).attr("id", this.listControlIdGenerator(this.listId, idValue, elementNumber));
            $($elementInputs[i]).attr("name", this.listControlNameGenerator(this.listId, nameValue, elementNumber));
    }
    //return prepared element of whatever
    return $element;
};

FormBuilder.prototype.reindexator = function(elementIndex) {
    console.log("FormBuilder ======================= reindexator( " + elementIndex +" )");
    //if no elements left - return
    var elementsCount = this.getListLength();
    if (elementsCount) {
        //get element at the index mark
        var $listElementDiv = $("#" + this.listId).children().eq(elementIndex);

        //go through all the elements lower than this element and do re-indexation
        for (var i=elementIndex; i<elementsCount; i++) {

            //reindex the element
            this.buildListElement($listElementDiv, this.$templateDiv, i);

            // jump on the next element
            $listElementDiv = $($listElementDiv).next();
        }
    }
};

FormBuilder.prototype.getListLength = function() {
    return $("#" + this.listId).children().length;
};

FormBuilder.prototype.listDivIdGenerator = function(listId, elementNumber) {
    return listId + "_" + elementNumber;
};

FormBuilder.prototype.listControlIdGenerator = function(listId, inputId, elementNumber) {
    return listId + elementNumber + "." + inputId;
};

FormBuilder.prototype.listControlNameGenerator = function(listId, inputName, elementNumber) {
    return listId + "[" + elementNumber + "]" + "." + inputName;
};