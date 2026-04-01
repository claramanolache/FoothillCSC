package stacks;

import java.lang.reflect.Type;

public class Navigator {
    String currentLink;
    StackList<String> backLinks;
    StackList<String> forwardLinks;
    /** Constructor for Navigator, initializes current link and stacks.
     * Current link is initialized to an empty string. Backlinks and forwardlinks
     * are initialized as empty stacks with appropriate names.
     */
    public Navigator(){
        currentLink = "";
        backLinks = new StackList<String>("Back Links");
        forwardLinks = new StackList<String>("Forward Links");
    }

    /**
     * Sets the current link to the given link name. Replaces the current link,
     * pushes the previous current link onto the back links stack, and clears the
     * forward links stack. Stops if the link name is null or the same as the current link.
     * @param linkName the name of the link to set as current
     */
    public void setCurrentLink(String linkName){
        if (linkName == null || linkName.equals(currentLink)){
            return;
        }
        backLinks.push(currentLink);
        currentLink = linkName;
        forwardLinks.clear();
    }
    /**
     * Goes back to the previous link by popping from the back links stack.
     * The current link is pushed onto the forward links stack.
     * If there are no back links, the method does nothing.
     */
    public void goBack(){
        if (backLinks.isEmpty()){
            return;
        }
        forwardLinks.push(currentLink);
        currentLink = backLinks.pop();
    }
    /**
     * Goes forward to the next link by popping from the forward links stack.
     * The current link is pushed onto the back links stack.
     * If there are no forward links, the method does nothing.
     */
    public void goForward(){
        if (forwardLinks.isEmpty()){
            return;
        }
        backLinks.push(currentLink);
        currentLink = forwardLinks.pop();
    }
    //* ACCESSOR METHODS *//
    /**
     * Returns the current link.
     * @return the current link as a String
     */
    public String getCurrentLink(){
        if (currentLink == null){
            return "";
        }
        return currentLink;
    }
    /**
     * Accessor for back links stack.
     * @return the back links as a StackList of Strings
     */
    public StackList<String> getBackLinks(){
        return backLinks;
    }
    /**
     * Accessor for forward links stack.
     * @return the forward links as a StackList of Strings
     */

    public StackList<String> getForwardLinks() {
        return forwardLinks;
    }

}
