# Fractal-Generator
Explore Fractals with a UI

MOTIVATION: I was originally writing code that would generate fractal image files, but figured it'd be better to be able to zoom in wherever into the fractal by just pointing and clicking. Hence, a UI was created at the end.

Though I could have used some libraries in lieu of creating some of these objects by hand, I did it for the purpose of getting more practice in and acquainted with Java and object-oriented programming (OOP).

As you might be able to tell, I am still learning about UI coding for Java and hence there may be some violations in some of the conventions used in Java for UI development. It does work the way it is supposed to, though I will change some of the code to satisfy such conventions as I become aware of them.

DESCRIPTION: The main image that is generated is a Mandelbrot set, and the image on the top left corner is the Julia set corresponding to the point at which the Mandelbrot set is centered.

The main controls are as follows:

Left-click - Zooms into location that's been clicked on Right-click - Zooms out of location Middle (wheel)-click - Resets the fractal view Dragging - Moves the image around

FURTHER NOTES: There are still some bugs I need to fix, and I also need to do some code cleanup. The project was not supposed to go nearly this far, so the last bit that I did, which was creating the UI, was sort of quick and not well-thought out.

A ComplexMath class was also started which would employ some of the most fundamental algorithms and mathematical identities to create functions, such as sin and exp, for complex numbers. Since this part of the project is more math-related and less OOP-related, I do not plan on working more on that class anytime soon. If I do find the time to do it, then some of the methods in the Complex class will be moved to the ComplexMath class as static methods.

Another area of interest to me are quaternions and octonions, and how images can be created with them. Creating classes for those would be another thing I might be interested in the future, if time permits.
