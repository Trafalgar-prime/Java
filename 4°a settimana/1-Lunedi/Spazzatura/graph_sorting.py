import turtle, math, random, time
from turtle import Turtle, Vec2D # A 2 dimensional vector class, in the very first lines of turtle.py
from typing import Union #, Type  # helps in the Type Hints annotation
                                # from versions 3.10 pipe operator (|) can be used instead
                                # of using Union. Check on realpython.com

SIZE = 50
W_HEIGHT = 9
W_WIDTH = 14
WINDOW_SIZE = (W_WIDTH * SIZE, W_HEIGHT * SIZE)
ARROWHEAD = 15
SCREEN_COLOR = "white"
GRAPH_AREA = ( (-326, 206), (77, -100) )
GAP = 5
TRUNC = 10 # decimal truncament
EPSILON = 1/(10**(TRUNC-4)) # = 0.000001 because sometime a certain gap must be allowed
POLY_DICT = {"circle" : 72,
             "triangle" : 3,
             "squared" : 4,
             "pentagon": 5,
             "hexagon": 6,
             "heptagon": 7,
             "octagon": 8
             }
BUTTONS_LIST = {}

#============================================================================================
"""The following patch is not mine. I found it from a certain Claudio on stackoverflow
    while I was searching for a way to rotate a text using the turtle module. Turtle is
    built on Tkinter, who can rotate text from version 8.6 (for checking run on terminal
    python -m tkinter).
    He saved me from loosing inside the Tkinter's maze because it is not between my research purpose
    using tkinter directly; my goal is to use what I have learned so far with at least a few
    little tip I saw here and there. I have understood that canvas was responsible about writing
    things in turtle, but because I don't know enough about programming I didn't want to modify
    anything on turtle.py.
    I'm leaving it here for two reasons:
    - It serves for some commands that I implemented and I don't want to spend time cleaning it
        just to erase this few line's patch;
    - This method taught me that I can modify other modules without touching them directly
    using aliasing."""
#============================================================================================
class Patch_txt_angle:
    def RawTurtleDOTwrite(self, arg, move=False, align="left", font=("Arial", 11, "normal"), txt_angle=0):
        if self.undobuffer:
            self.undobuffer.push(["seq"])
            self.undobuffer.cumulate = True
        end = self._write(str(arg), align.lower(), font, txt_angle)
        if move: x, y = self.pos() ; self.setpos(end, y)
        if self.undobuffer: self.undobuffer.cumulate = False
    def RawTurtleDOT_write(self, txt, align, font, txt_angle):
        item, end = self.screen._write(self._position, txt, align, font, self._pencolor, txt_angle)
        self.items.append(item)
        if self.undobuffer: self.undobuffer.push(("wri", item))
        return end
    def TurtleScreenBaseDOT_write(self, pos, txt, align, font, pencolor, txt_angle):
        x, y = pos ; x = x * self.xscale ; y = y * self.yscale
        anchor = {"left":"sw", "center":"s", "right":"se" }
        item = self.cv.create_text(x-1, -y, text = txt, anchor = anchor[align],
            fill = pencolor, font = font, angle = txt_angle)
        x0, y0, x1, y1 = self.cv.bbox(item)
        self.cv.update()
        return item, x1-1

turtle.RawTurtle.write         = Patch_txt_angle.RawTurtleDOTwrite
turtle.RawTurtle._write        = Patch_txt_angle.RawTurtleDOT_write
turtle.TurtleScreenBase._write = Patch_txt_angle.TurtleScreenBaseDOT_write

# example
#tt = turtle.Turtle()
#tt.write("EXAMPLE", font=("Arial", 12, "bold"), align="right", txt_angle=45)
## the prewious call doesn't affect the following calls.
## infact we can call a write without specifing an angle and it will be not rotated
#tt.forward(100)
#tt.write("EXAMPLE", font=("Arial", 12, "bold"), align="right")

#============================================================================================
#============================== END_OF_CLAUDIO'S_PATCH ======================================
#============================================================================================

class Patch_Vec2D_rotation:
    def rotate(self, angle, center = (0,0)):
        """rotate self counterclockwise by angle relative to an application point that could be
            different from the origin (0,0), because I need the possibility to have an occasional
            relative rotational center and to avoid calculus when zero angle is given.
        """
        if angle == 0:
            return self
        else:
            angle = math.radians(angle)
            c, s = math.cos(angle), math.sin(angle)
            if center == (0,0):
                return Vec2D(self[0]*c-self[1]*s,self[1]*c+self[0]*s)
            else:
                return Vec2D((self[0]-center[0])*c-(self[1]-center[1])*s+center[0],(self[1]-center[1])*c+(self[0]-center[0])*s+center[1])

turtle.Vec2D.rotate = Patch_Vec2D_rotation.rotate

class Stack:
    def __init__ (self):
        self.items = []

    def push (self, item):
        self.items.append (item)

    def pop (self):
        if self.isEmpty():
            raise ValueError ("Pop from empty stack")
        return self.items.pop()

    def peek (self):
        if self.isEmpty():
            raise ValueError ("Peeking into an empty stack")
        return self.items[-1]

    def isEmpty (self):
        return len (self.items) == 0

    def size (self):
        return len (self.items)

class Graph:
    def __init__ (self):
        self.adj_lists = {}

    def add_vertex (self, key):
        self.adj_lists [key] = {}

    def add_edge (self, from_, to, weight = 1):
        self.adj_lists [from_][to] = weight

    def vertices (self):
        return self.adj_lists.keys()

    def neighbors (self, key):
        return self.adj_lists[key].keys()

    def weight (self, v_from, v_to):
        return self.adj_lists[v_from].get(v_to, 0)

class Polygon: #convex
    """every new polygon is built around a tangent circle, to be able to put a label inside"""
    def __init__ (self, shape: Union[str,int], pos: tuple[float,float]=(0,0), size: float=1, size_h: float=1, angle: float=0, regular: bool=True):
        self._regular = bool(regular)
        self._center = Vec2D (pos[0], pos[1])
        self._angle = float(angle)
        self._width = float(size)
        if not self.is_regular () and isinstance(float(size_h),float):
            self._height = float(size_h)
        else:
            self._height = float(size)
        self.change_shape (shape) # defines three variables

    def _nominate_shape (self, shape: Union[str, int]) -> tuple[str, int]:
        if isinstance (shape, str) and shape in POLY_DICT:
                return shape, POLY_DICT [shape]
        elif isinstance (int(shape), int):
            vertices = int(shape)
            found = False
            for name in POLY_DICT:
                if vertices == POLY_DICT[name]:
                    found = True
                    shape = name
            if not found:
                return "generic_poly", vertices
            return shape, vertices
        else:
            raise TypeError (f"{shape} is not a valide name\ninstert at least an integer >= 3")

    def get_shape (self) -> str:
        return self._shape

    def get_number_vertices (self) -> int:
        return self._vertices

    def get_edges (self) -> list[Vec2D]: #return a copy
        poly = self._vertices_list [:]
        return poly

    def get_center (self) -> Vec2D:
        return self._center

    def get_angle (self) -> float:
        return self._angle

    def get_sizes (self) -> tuple[float,float]:
        return self.get_width (), self.get_height ()

    def get_height (self) -> float:
        return self._height

    def get_width (self) -> float:
        return self._width

    def is_regular (self) -> bool:
        return self._regular

    def make_it_regular (self, flag: bool) -> None:
        self._regular = bool (flag)
        if self.is_regular ():
            self._make_it_regular ()

    def _make_it_regular (self) -> None:
        w, h = self.get_sizes ()
        m = max (w,h)
        d_w = (w - m) / m *100
        d_w = truncate (d_w)
        if d_w <= EPSILON:
            d_w = 0
        d_h = (h - m) / m *100
        d_h = truncate (d_h)
        if d_h <= EPSILON:
            d_y = 0
        self.resize_percent (d_w, d_h)

    def _make_vertices_list (self) -> list[Vec2D]:
        shape = self.get_shape()
        n = self.get_number_vertices ()
        angle = 0
        d_angle = 360 / n
        if shape == "circle":
            size = SIZE / 2
        else:
            """the following calculus generates an error = math.pi / n
                in the following rotation (and only for this one time) made for putting
                the figure on a base side and I don't know why.
                I only figured out the consequences by sampling the figures
                from 3 to 20 vertices to reveal the pattern (it was not immediate because
                for 1 and 2 it can't be calculated, because is based on the formula
                cathetus = hypotenuse * sin (opposite_angle) ).
                Probably is the conversion in radians, but I can't be sure
                I only know that it will not appen without this resizing, but
                the consequence is that will be the distance center-vertex to be
                equal to SIZE/2 (as in circle) and not half of a side. Choosing this way
                for the other will influence the resizing made to fit text inside
                the Button and the possibility to use the SquaredArea properly,
                because it can't be possible to put in 2 specific coordinates
                and returning a specific area fitted in that corners"""
            #angle = math.radians(d_angle/2) #for debug checking
            angle = math.pi/n  # = ( (2*math.pi) /n) /2
            sin_a = math.sin(angle) # radians
            cat = SIZE/2
            size = cat/sin_a # so now each side measures 1 SIZE unit
        P = Vec2D(size,0)
        lst = [Vec2D(0,0)] * n
        for i in range(len(lst)):
            lst[i] += P.rotate (angle)
            angle += d_angle
        if shape != "circle":
            _error_ = math.pi / n
            d_angle = d_angle / 2 + 90 + _error_ # degree
            rotate_list (lst, -d_angle)
        return lst

    def _apothem (self) -> float:
        """this function is called just before vertices' list is done, so, in theory,
            the first side is made parallel to the x axes by the contructor (except for circle),
            the apothem (for definition) is perpendicular to each side, and the figure
            is centered in the origin. So..."""
        poly = self.get_edges ()
        c_x = c_y = 0                # I am putting this line just to be better readable
        shape = self.get_shape ()
        if shape == 'circle': # is the only figure that is not rotated by the contructor
            apothem = abs (c_x - poly[0][0])
        else:
            apothem = abs (c_y - poly[0][1])
        ##=============== for debug checking =========================================
        #m_base, q_base = line_slope_intercept (poly[0], poly[1])
        #if m_base == 0:
            #m_apothem = None
            #q_apothem = c_x
        #elif  m_base is None:
            #m_apothem = 0
            #q_apothem = c_y
        #else:
            #m_apothem = -1 / m_base
            #q_apothem = c_y - m_apothem * c_x
        #intersection = two_lines_interception ((m_base,q_base), (m_apothem,q_apothem))
        #apothem = distance ((c_x, c_y), intersection)
        ##=============================================================================
        return apothem

    def _rototranslate (self, dir_vect = None, angle = None, center = (0,0)) -> None:
        rototranslate_list (self._vertices_list, dir_vect, angle, center)

    def rototranslate (self, to: tuple[float,float] = None, angle: float = None, center: tuple[float,float] = None) -> None:
        if to is not None:
            to = Vec2D (to[0], to[1])
            dir_vect = to - self.get_center ()
        else:
            dir_vect = None
        if center is None:
            center = self.get_center()
        self._rototranslate (dir_vect, angle, center)
        if angle is not None:
            self._angle += angle
        if dir_vect is not None:
            self._center = to

    def _vect_transform (self, dir_vect: tuple[float, float], center: tuple[float,float] = None, angle: float = None) -> None:
        if center is None:
            center = self.get_center ()
        if angle is None:
            angle = self.get_angle ()
        if dir_vect != (0,0):
            vect_transform_list (self._vertices_list, dir_vect, center, angle)

    def resize_percent (self, percent: float, percent_h: float = None) -> None:
        if percent < -1:
            raise ValueError ("A figure can't be reduced over 100%")
        size_w = percent
        if percent_h is not None:
            if percent_h < -1:
                raise ValueError ("A figure can't be reduced over 100%")
            size_h = percent_h
            if self.is_regular ():
                size_h = size_w = max (size_h, size_w)
        else:
            size_h = size_w
        size = Vec2D(size_w, size_h)
        self._vect_transform (size)
        self._width  = truncate(self._width  * (1 + size_w))
        self._height = truncate(self._height * (1 + size_h))

    def reflect_on_axis (self, axis: tuple[float,float]) -> None:
        axis_m, axis_q = axis
        c_x, c_y = self.get_center ()
        if axis_m is None:
            axis_angle = math.pi / 2
            perp_m = 0
            perp_q = c_y
        else:
            axis_angle = math.atan (axis_m)
            if axis_m == 0:
                perp_m = None
                perp_q = c_x
            else:
                perp_m = - 1 / axis_m
                perp_q = c_y - perp_m * c_x
        interception = two_lines_interception (axis, (perp_m, perp_q))
        interception = Vec2D (interception[0], interception[1])
        #self._vect_transform ((-2,0), interception, (axis_angle*180/math.pi)-90)     # for debug checking
        c, s = math.cos(2 * axis_angle), math.sin(2 * axis_angle)
        for p in range(len(self._vertices_list)):
            t_p = self._vertices_list[p] - interception
            x, y = t_p
            r_t_p = Vec2D (x*c + y*s, x*s - y*c)
            self._vertices_list[p] = r_t_p + interception
        self._angle = 180 - self._angle
        self._center = 2*interception - self._center

    def reflect_on_point (self, point: tuple[float,float] = None) -> None:
        if point is None:
            point = self.get_center()
        else:
            point = Vec2D (point[0], point[1])
        #self._vect_transform ((-2,-2), point)       # for debug checking
        for p in range (len(self._vertices_list)):
            self._vertices_list[p] = 2*point - self._vertices_list[p]
        self._angle += 180
        self._center = 2*point - self._center

    def change_shape (self, shape: Union[str, int]) -> None:
        self._shape, self._vertices = self._nominate_shape (str(shape))
        self._vertices_list = self._make_vertices_list () # regular polygon
        apothem = self._apothem ()
        width = height = apothem*2 /SIZE
        d_w = percent_change ( width, self._width )
        d_h = percent_change (height, self._height)
        self._vect_transform ( dir_vect=(d_w, d_h), center=(0,0) )
        self._rototranslate (dir_vect = self._center, angle = self._angle)

    def __contains__ (self, this_point: tuple [float, float]) -> bool:
        if isinstance(this_point, Vec2D):
            center = self.get_center ()
            if self.get_shape () == "circle":
                width, height = self.get_sizes ()
                if width == height:
                    d_p_pow_2 = distance_squared (center, this_point)
                    r_pow_2 = math.pow ((width / 2) * SIZE, 2)
                    return d_p_pow_2 - r_pow_2 <= EPSILON
                else:
                    f_1, f_2, double_a, double_b = ellipse_focals_axes (center, width, height, self.get_angle())
                    d_p_1 = distance (f_1, this_point)
                    d_p_2 = distance (f_2, this_point)
                    return (d_p_1 + d_p_2 - double_a) <= EPSILON
            else:
                vertices = self.get_edges()
                inside = False
                line_1 = line_slope_intercept (center, this_point)

                line_2 = line_slope_intercept (vertices [-1], vertices [0])
                interception = two_lines_interception (line_1, line_2)
                if is_on_segment (interception, vertices [-1], vertices [0]):
                    #inside = is_on_segment (this_point, interception, center) # for debug checking
                    lower_x, upper_x = min (center[0], interception[0]), max (center[0], interception[0])
                    lower_y, upper_y = min (center[1], interception[1]), max (center[1], interception[1])
                    check_1 = (lower_x <= this_point[0] <= upper_x)
                    check_2 = (lower_y <= this_point[1] <= upper_y)
                    inside =  check_1 and check_2

                i = 0
                while i < (len (vertices) - 1) and not inside:
                    line_2 = line_slope_intercept (vertices [i], vertices [i + 1])
                    interception = two_lines_interception (line_1, line_2)
                    if is_on_segment (interception, vertices [i], vertices [i + 1]):
                        #inside = is_on_segment (this_point, interception, center) # for debug checking
                        lower_x, upper_x = min (center[0], interception[0]), max (center[0], interception[0])
                        lower_y, upper_y = min (center[1], interception[1]), max (center[1], interception[1])
                        check_1 = (lower_x <= this_point[0] <= upper_x)
                        check_2 = (lower_y <= this_point[1] <= upper_y)
                        inside =  check_1 and check_2
                    i += 1

                return inside
        else:
            raise TypeError ("Only a point (x,y) is accepted")

class Label(Turtle):
    _font_style = ("normal", "bold", "italic")
    def __init__ (self, text, color = "black", font = "Arial", size = 8, style = "normal"):
        super().__init__(visible=False)
        self.pencolor(color)
        self.penup()
        if isinstance(text,str):
            self._text = text
        else:
            self._text = ""
        if isinstance(font,str):
            self._font = font
        else:
            raise TypeError ("Font name must be a string format")
        if isinstance(size,float) or isinstance(size,int):
            self._size = size
        else:
            raise TypeError ("Font size must be a number")
        if style in self._font_style:
            self._style = style
        else:
            raise ValueError ("Font style can only be 'normal', 'bold' or 'italic'")

    def hide_label (self) -> None:
        self.clear ()

    def show_label (self, center: Vec2D = None, angle = 0) -> None:
        if center is None:
            center = self.pos()
        self._write_in (center, angle)

    def _write_in (self, center: Vec2D, angle) -> None:
        c_x, c_y = center
        y = c_y  - (self._size * 0.75)
        x = c_x + 1.33
        new_center = Vec2D (x, y)
        new_center = new_center.rotate(angle, center)
        self.goto (new_center)
        self.write (self._text, font = self.get_font_info(), align = "center", txt_angle = angle)

    def get_text (self) -> str:
        return self._text

    def get_font_info (self) -> tuple[str,float,str]:
        return self._font, self._size, self._style

    def set_text (self, text) -> None:
        if isinstance (text,str):
            self._text = text

    def set_font_info (self, font:str = None, size:float = None, style:str = None) -> None:
        if isinstance(font,str):
            self._font = font
        if (isinstance(size,float) or isinstance(size,int)):
            self._size = size
        if style in self._font_style:
            self._style = style

    def set_color (self, color) -> None:
        self.pencolor(color)

class Button(Turtle, Polygon): # search Multiple Inheritance and Method Resolution Order (MRO) for more info
    def __init__ (self, label = None, action = None, pos = (0, 0), shape = "squared", size = None, size_h = None, angle = 0, bgcolor = SCREEN_COLOR, pencolor = "black", regular = False):
        Turtle.__init__(self, visible=False)
        self._default_pencolor = pencolor
        self.pencolor(self._default_pencolor)
        self.penup()
        self._visible = False
        self._bgcolor = bgcolor
        self._label = Label (text=label)
        Polygon.__init__(self, shape=shape, pos=pos, angle=angle, regular=regular)
        if self._label.get_text () != "":
            self.adapt_on_label ()
        if size is not None:
            if self.is_regular() or size_h is None:
                self.change_sizes (size, size)
            else:
                self.change_sizes (size, size_h)
        self._action = action
        self._selected = False
        BUTTONS_LIST[self] = self

    def _draw_area (self) -> None:
        if self.is_visible ():
            lst = self.get_edges ()
            self.penup()
            self.fillcolor(self._bgcolor)
            self.goto (lst[0])
            self.pendown()
            self.begin_fill()
            for pnt in lst[::-1]:
                self.goto(pnt)
            self.end_fill()
            self.penup()
            self.goto (self.get_center())

    def hide_button (self) -> None:
        self.clear ()
        self.hide_label ()
        self._visible = False

    def hide_label (self) -> None:
        self._label.hide_label()

    def show_button (self) -> None:
        self._visible = True
        self._draw_area()
        self.show_label ()

    def show_label (self) -> None:
        if self.is_visible ():
            self._label.show_label ( self.get_center(), self.get_angle() )

    def refresh (self) -> None:
        self.clear()
        self.hide_label()
        self._draw_area()
        self.show_label()

    def get_label (self) -> str:
        return self._label.get_text()

    def get_label_info (self) -> tuple[str,float,str]:
        return self._label.get_font_info()

    def do (self, x, y) -> None:
        self._action (x, y)

    def is_visible (self) -> bool:
        return self._visible

    def is_selected (self) -> bool:
        return self._selected

    def set_regularity (self, flag: bool) -> None:
        self.make_it_regular (bool (flag))
        if self.is_regular ():
            self.refresh()

    def _set_sizes (self, width = None, height = None) -> tuple[float,float]:
        w, h = self._adapt_on_label()
        t = self._label.get_text()
        if  width is None or (t != "" and  width < w):
            width = self.get_width()
        if height is None or (t != "" and height < h):
            height = self.get_height()
        if width <= 0 or height <= 0:
            raise ValueError ("Dimensions can't be negative and zero neither")
        return width,height

    def change_sizes (self, width = None, height = None) -> None:
        w, h = self.get_sizes ()
        width, height = self._set_sizes (width, height)
        d_w = percent_change (w, width)
        d_h = percent_change (h, height)
        if d_w != 0 or d_h != 0:
            self.clear ()
            self.hide_label()
            self.resize_percent (d_w, d_h)
            self._draw_area ()
            self.show_label ()

    def _adapt_on_label (self) -> tuple[float,float]:
        text = self._label.get_text()
        w = self.get_width()
        h = self.get_height()
        if text != "":
            font_info = self._label.get_font_info()
            font_size = font_info[1]
            self._label.goto(0,-2000) # to keep the glitch unseen
            start = self._label.pos()
            self._label.write(text, align = "left", font = font_info, move =True)
            stop  = self._label.pos()
            self._label.undo()
            width  = truncate((5 + abs(stop[0]-start[0]))/SIZE)
            height = truncate( (5 + font_size)/SIZE )
            if self.get_shape() != "squared":
                if not self.is_regular():
                    width, height = ellispe_from_squared_label (width, height)
                else:
                    diagonal = math.sqrt(math.pow(width,2) + math.pow(height,2))
                    width = height = truncate(diagonal)
        else:
            width,height = w,h
        return float(width), float(height)

    def adapt_on_label (self) -> None:
        self.change_sizes ( *self._adapt_on_label() )

    def set_label (self, text:str = None, font:str = None, size:float = None, style:str = None, color:str = None) -> None:
        self._label.set_text (text)
        self._label.set_font_info (font, size, style)
        if color is not None:
            self._label.set_color (color)
        self.adapt_on_label ()

    def change_shape (self, shape: Union[str,int]) -> None:
        Polygon.change_shape (self, shape)
        self.refresh()

    def set_action (self, action) -> None:
        self._action = action

    def set_selected (self, flag) -> None:
        self._selected = flag

    def _change_pencolor (self, color = None) -> None:
        if new_color is None:
            self._t.pencolor (self._default_pencolor)
        else:
            self._t.pencolor(color)

    def set_pencolor (self, color) -> None:
        self._default_pencolor = color
        self._change_pencolor ()

    def set_bgcolor (self, color) -> None:
        self._bgcolor = color

    def get_bgcolor (self) -> str:
        return self._bgcolor

    def move_to (self, pos = None, angle = None, center = None) -> None:
        if pos is None:
            pos = self.get_center()
        if not isinstance (pos,tuple):
            raise TypeError ("Button.move_to() takes one tuple (x,y) as new position")
        self.rototranslate (pos, angle, center)
        self.refresh()

    def touch (self, other:'Button') -> bool:
        if other.is_visible():  # self.is_visible()
            if Polygon.__contains__(self, other.get_center()) or Polygon.__contains__(other, self.get_center()):
                return True
            else:
                inside = False
                i = 0
                lst = self.get_edges()
                while i < len(lst) and not inside:
                    inside = Polygon.__contains__(other, lst[i])
                    i += 1
                if inside:
                    return True
                else:
                    i = 0
                    lst = other.get_edges()
                    while i < len(lst) and not inside:
                        inside = Polygon.__contains__(self, lst[i])
                        i += 1
                    return inside
        return False

    def __str__ (self) -> str:
        return self.get_label()

    def __contains__ (self, point) -> bool:
        if self.is_visible():
            return Polygon.__contains__(self, this_point=point)

class SquaredArea (Button):
    def __init__(self, point_1, point_2):
        self._left_x, self._right_x  = min(point_1[0],point_2[0]), max(point_1[0],point_2[0])
        self._lower_y, self._upper_y = min(point_1[1],point_2[1]), max(point_1[1],point_2[1])
        pos = Vec2D ((self._left_x+self._right_x) /2, (self._lower_y+self._upper_y) /2)
        size = abs (self._right_x - self._left_x) /SIZE
        size_h = abs (self._upper_y - self._lower_y) /SIZE
        super().__init__(pos = pos, size = size, size_h = size_h)

    def get_corners_coordinates (self) ->tuple[float,float,float,float]:
        return self._left_x, self._right_x, self._lower_y, self._upper_y

SquaredArea.hide_area = Button.hide_button
SquaredArea.show_area = Button.show_button

class Arrow(Turtle):
    """A graphical Class only made for mutual supporting to Link Class."""
    _alias_axis = {"y" : "y", "up" : "y", "down" : "y", "x" : "x", "left" : "x", "right" : "x"}

    def __init__ (self, link, corner = 0, color = "black", axis = "y"):
        super().__init__(visible=False)
        self.pencolor(color)
        self.penup()
        if corner > 2:
            raise ValueError ("An Arrow can have 0, 1, or 2 corner \nMaybe one day there will be more, but for now this is what the house can serve")
        self._link = link
        self._corner = corner
        self._axis = self._set_axis (axis)
        self._point_list = self._make_point_list ()
        self._visible = False

    def _set_axis (self, axis) -> Union[str,None]:
        if self._corner == 0:
            return "y"
        elif axis in self._alias_axis:
            return self._alias_axis [axis]
        else:
            raise ValueError ("axis can only be 'x' or 'y' ")

    def _draw_line (self) -> None:
        if self.is_visible () and self._link._weight > EPSILON:
            lst = self.get_points ()
            self.goto (lst[0])
            start, end = self._link.get_extremes ()
            if start != end:
                self.pendown ()
                for p in lst[1:]:
                    self.goto (p)
            else:
                radius = abs(lst[0][0] - lst[1][0])
                self.setheading(90)
                self.forward(start.get_height()*SIZE)
                self.pendown ()
                self.circle(radius, 270)
                self.setheading(0)
            self.penup()

    def _draw_arrowhead (self) -> None:
        heads = self._link.get_orientation ()
        if self.is_visible () and heads !=0 and self._link._weight > EPSILON:
            start_button, end_button = self._link.get_extremes()
            lst = self.get_points ()
            if start_button != end_button:
                start, end = lst[-2],lst[-1]
                m,q = line_slope_intercept (start, end)
                if m is None:
                    if start [1] < end [1]:
                        ang = 90
                    else:
                        ang = -90
                else:
                    ang = math.atan (m) * 180/math.pi
                    if m > 0 and start [1] > end [1]:
                        ang = (-1 * get_sign(ang)) * 180 + ang
                    elif m < 0 and start [1] < end [1]:
                        ang = (-1 * get_sign(ang)) * 180 + ang
                    elif m == 0 and start [0] > end [0]:
                        ang = 180
            else:
                end = lst[-1]
                ang = -30
            self.goto (end)
            self.pendown ()
            self.left (ang + 30)
            self.fillcolor (self.pencolor())
            self.begin_fill ()
            self.backward (ARROWHEAD)
            self.left (60)
            self.forward (ARROWHEAD)
            self.left (60)
            self.backward (ARROWHEAD)
            self.end_fill ()
            self.penup ()
            self.setheading (0)

    def _anchor_point (self, button: Button, ref_point: Vec2D) -> Vec2D:
        line_1 = line_slope_intercept (button.get_center (), ref_point)
        if button.get_shape () == "circle":
            button_ellipse = ellipse_focals_axes ( button.get_center (), button.get_width (), button.get_height (), button.get_angle () )
            anchor_1, anchor_2 = line_ellipse_interception (line_1, button_ellipse)
            if is_on_segment (anchor_1, button.get_center (), ref_point):
                anchor_point = anchor_1
            else:
                anchor_point = anchor_2
        else:
            vertices = button.get_edges ()
            found = False
            line_2 = line_slope_intercept (vertices [-1], vertices [0])
            interception = two_lines_interception (line_1, line_2)
            check_1 = is_on_segment (interception, vertices [-1], vertices [0])
            check_2 = is_on_segment (interception, button.get_center (), ref_point)
            found = check_1 and check_2
            i = 0
            while i < (len (vertices) - 1) and not found:
                line_2 = line_slope_intercept (vertices [i], vertices [i + 1])
                interception = two_lines_interception (line_1, line_2)
                check_1 = is_on_segment (interception, vertices [i], vertices [i + 1])
                check_2 = is_on_segment (interception, button.get_center (), ref_point)
                found = check_1 and check_2
                i += 1
            anchor_point = interception
        return anchor_point

    def _make_point_list (self, corner = None, axis = None) -> list[Vec2D]:
        if self._link._weight > EPSILON:
            start_button, end_button = self._link.get_extremes ()
            start_center = start_button.get_center()
            if start_button != end_button:
                if corner == None:
                    corner = self._corner
                end_center = end_button.get_center ()
                if corner == 0:
                    start_point = self._anchor_point (start_button, end_center)
                    end_point = self._anchor_point (end_button, start_center)
                    return [start_point, end_point]
                else:
                    if axis == None:
                        axis = self._axis
                    if corner == 1:
                        if axis == "y":
                            mid = Vec2D (start_center[0], end_center[1])
                            if mid in start_button: # switch exit axis
                                lst = self._make_point_list (corner = 1, axis = "x")
                            else:
                                if mid in end_button:
                                    lst = self._make_point_list (corner = 2, axis = "y")
                                else:
                                    start_point = self._anchor_point (start_button, mid)
                                    end_point = self._anchor_point (end_button, mid)
                                    return [start_point, mid, end_point]
                        else: # axis == "x"
                            mid = Vec2D (end_center[0], start_center[1])
                            if mid in start_button: # switch exit axis
                                lst = self._make_point_list (corner = 1, axis = "y")
                            else:
                                if mid in end_button:
                                    lst = self._make_point_list (corner = 2, axis = "x")
                                else:
                                    start_point = self._anchor_point (start_button, mid)
                                    end_point = self._anchor_point (end_button, mid)
                                    return [start_point, mid, end_point]
                    else: # corner == 2
                        mid = (start_center + end_center) * (1/2)
                        if axis == "y":
                            if Vec2D (start_center[0], end_center[1]) in start_button: # switch exit axis
                                lst = self._make_point_list (corner = 2, axis = "x")
                            else:
                                mid_1 = Vec2D (start_center [0], mid [1])
                                mid_2 = Vec2D (end_center [0], mid [1])
                                start_point = self._anchor_point (start_button, mid_1)
                                end_point = self._anchor_point (end_button, mid_2)
                                return [start_point, mid_1, mid_2, end_point]
                        else:
                            if Vec2D (end_center[0], start_center[1]) in start_button: # switch exit axis
                                lst = self._make_point_list (corner = 2, axis = "y")
                            else:
                                mid_1 = Vec2D (mid [0], start_center[1])
                                mid_2 = Vec2D (mid [0], end_center [1])
                                start_point = self._anchor_point (start_button, mid_1)
                                end_point = self._anchor_point (end_button, mid_2)
                                return [start_point, mid_1, mid_2, end_point]
            else:
                mid_1 = Vec2D(start_center[0],start_center[1]+start_button.get_height()) # upper
                mid_2 = Vec2D(start_center[0]+start_button.get_width(),start_center[1]) # righter
                start_point = self._anchor_point(start_button, mid_1)
                end_point = self._anchor_point(start_button, mid_2)
                lst = [start_point, end_point]
            return lst
        else:
            return None

    def get_points (self) -> tuple[Vec2D]:
        points = tuple(self._point_list)
        return points

    def is_visible (self) -> None:
        return self._visible

    def show_arrow (self) -> None:
        self._visible = True
        self._draw_line ()
        self._draw_arrowhead ()

    def hide_arrow (self) -> None:
        self.clear ()
        self._visible = False

    def refresh (self) -> None:
        self.clear ()
        self._point_list = self._make_point_list ()
        self._draw_line ()
        self._draw_arrowhead ()

    def change_form (self, corner = None, axis = None) -> None:
        if corner is not None:
            self._corner = corner
        self._axis = self._set_axis (axis)
        self.refresh ()

    def change_color (self, color) -> None:
        self.hide_arrow ()
        self.pencolor (color)
        self.show_arrow ()

class Link(Arrow): # an edge
    def __init__ (self, start: 'Button', end: 'Button', oriented = True, weight: float = 1, corner = 0, axis = "y", color = "black"):
        self._start = start
        self._end = end
        if weight < 0:
            raise ValueError ("Weight's link can't be negative")
        self._oriented = bool(oriented)
        self._weight = weight
        Arrow.__init__(self, link = self, corner = corner, color = color, axis = axis)

    def get_extremes (self) -> tuple[Button,Button]:
        return self._start, self._end

    def get_start (self) -> Button:
        return self._start

    def get_end (self) -> Button:
        return self._end

    def get_orientation (self) -> bool:
        return self._oriented

    def get_weight (self) -> float:
        return self._weight

    def set_orientation (self, orientation) -> None:
        self._oriented = bool(orientation)

    def set_weight (self, weight) -> None:
        if weight < 0:
            raise ValueError ("Weight's link can't be negative")
        self._weight = float(weight)

    def __contains__ (self, button) -> bool:
        return button in self.get_extremes()

    def __str__ (self) -> str:
        start, end = self.get_extremes()
        string = f"{str(start)} -> {str(end)}"
        return string

class GraphicalGraph (Button):
    def __init__ (self, graph, area, label, oriented = True, corner = 0, axis = "y"):
        Button.__init__(self, label=label)
        self._rawgraph = graph
        self._graph_area = area
        self._graph_vertices = {}
        for key in self._rawgraph.vertices(): #key is a string
            self._graph_vertices[key] = Button (key, shape = 'circle', regular = True)
            self._graph_vertices[key].set_label(size = 20, style = 'bold')
            self._graph_vertices[key].set_action(do_nothing)
        self._links = self._make_links_list (oriented, corner, axis)

    def _make_links_list(self, oriented, corner, axis) -> dict:
        vertex = self._graph_vertices
        lst = {}
        for v in self._rawgraph.vertices():
            for n in self._rawgraph.neighbors(v):
                weight = self._rawgraph.weight(v,n)
                link = Link (vertex[v], vertex[n], oriented, weight, corner, axis)
                lst[link] = link
        return lst

    def get_graph_area (self) -> SquaredArea:
        return self._graph_area

    def get_vertices (self) -> dict:
        return self._graph_vertices

    def get_neighbors (self, vertex) -> list:
        lst = [link.get_end() for link in self._links if vertex is link.get_start()]
        return lst

    def get_rawgraph (self) -> Graph:
        return self._rawgraph

    def show_graph (self) -> None:
        vertex = self._graph_vertices
        for v in vertex:
            vertex[v].show_button()
        for link in self._links:
            self._links[link].show_arrow()

    def hide_graph (self) -> None:
        vertex = self._graph_vertices
        for v in vertex:
            vertex[v].hide_button()
        for link in self._links:
            self._links[link].hide_arrow()

    def new_draw (self, x, y) -> None:
        vertex = self._graph_vertices
        for button in BUTTONS_LIST:
            if isinstance (button,GraphicalGraph):
                button.hide_graph()
                if button is not self:
                    button.set_bgcolor(SCREEN_COLOR)
                    button.refresh()
                    button._selected = False
        self.set_bgcolor ('yellow')
        self.refresh()
        self._selected = True
        x_left, x_right, y_down, y_up = self._graph_area.get_corners_coordinates()
        for v in vertex:
            wrong_pos = True
            while wrong_pos:
                x = random.randrange(int(x_left), int(x_right))
                y = random.randrange(int(y_down), int(y_up))
                position = Vec2D (x,y)
                new_center = check_new_position (self._graph_area, vertex[v], position)
                vertex[v].move_to (new_center)
                wrong_pos = False
                for m in vertex:
                    if vertex[m] is not vertex[v]:
                        wrong_pos = wrong_pos or vertex[v].touch(vertex[m])
            vertex[v].show_button()
        for link in self._links:
            self._links[link].refresh()
            self._links[link].show_arrow()

class GraphicalStack (Button):
    def __init__ (self, label, pos, direction = 'up'):
        super().__init__(label = label, pos = pos, shape = 'squared')
        self.set_label (size = 20, style = 'bold')
        self._direction = direction
        self._items = []

    def push (self, item) -> None:
        if self.isEmpty():
            c_x, c_y = self.get_center()
            w, h = self.get_sizes()
        else:
            p_item = self.peek()
            c_x, c_y = p_item.get_center()
            w, h = self.get_sizes()
        d = self._direction
        if d == 'up':
            x = c_x
            y = c_y - (h*SIZE + 5)
        elif d == 'down':
            x = c_x
            y = c_y - (h*SIZE + 5)
        elif d == 'right':
            x = c_x + (w*SIZE + 5)
            y = c_y
        else: # d == 'left'
            x = c_x - (w*SIZE + 5)
            y = c_y
        b_item = Button (label = str(item), pos = (x, y) )
        b_item.set_label(size = 20, style = 'bold')
        b_item.show_button()
        self._items.append (b_item)

    def pop (self) -> Button:
        if self.isEmpty():
            raise ValueError ("Pop from empty stack")
        p_item = self.peek()
        p_item.hide_button()
        return self._items.pop()

    def peek (self) -> Button:
        if self.isEmpty():
            raise ValueError ("Peeking into an empty stack")
        return self._items[-1]

    def bottom (self) -> Button:
        if self.isEmpty():
            raise ValueError ("Peeking into an empty stack")
        return self._items[0]

    def isEmpty (self) -> bool:
        return len(self._items) == 0

    def size (self) -> int:
        return len (self._items)

    def __str__(self) -> str:
        lst = [str(n) for n in self._items]
        return str(lst)

GStack = GraphicalStack

#==================================================================================
#======================== MATHEMATICAL FUNCTIONS ==================================
#==================================================================================

def distance_squared (from_point: tuple [float, float], to_point: tuple [float, float]) -> float:
    p_x, p_y = from_point
    q_x, q_y = to_point
    dist_sqr = math.pow((q_x - p_x), 2) + math.pow((q_y - p_y), 2)
    round_dist_sqr = truncate(dist_sqr)
    return round_dist_sqr

def distance (from_point: tuple [float, float], to_point: tuple [float, float]) -> float:
    dist = math.sqrt(distance_squared (from_point, to_point))
    round_dist = truncate (dist)
    return round_dist

def get_sign (n) -> int:
    if n != 0:
        return n/abs(n)
    return n

def truncate (n, decimals=TRUNC) -> float:
    multiplier = 10**decimals
    sign = get_sign (n)
    tr = int(abs(n) * multiplier + 0.05) / multiplier
    if tr <= EPSILON:
        tr = 0
    return sign * tr

def percent_change (init_val: float, fin_val: float) -> float:
    d_p = (fin_val/init_val -1)
    d_p = truncate (d_p)
    return d_p

def is_on_segment (point: tuple [float, float], end_point_1: tuple [float,float], end_point_2: tuple[float, float]) -> bool:
    if point != (None,None):
        m, q = line_slope_intercept (end_point_1, end_point_2)
        x, y = point
        a_x, a_y = end_point_1
        b_x, b_y = end_point_2
        check_1 = (m is not None and abs (y - a_y) <= EPSILON)
        if check_1:
            lower_x, upper_x = min (a_x, b_x), max (a_x, b_x)
            return lower_x <= x <= upper_x
        check_2 = (m is None     and abs (x - a_x) <= EPSILON)
        check_3 = (m is not None and abs (m*x + q - y) <= EPSILON)
        if check_2 or check_3:
            lower_y, upper_y = min (a_y, b_y), max (a_y, b_y)
            return lower_y <= y <= upper_y
        else:
            return False
    else:
        return False

def line_ellipse_interception (line: tuple[float, float], ellipse: tuple[Vec2D,Vec2D,float,float]) -> Union [tuple [Vec2D, Vec2D], tuple [None, None]]:
    """I am sure that this function could have been better coded, but I have to move over for now.
        At least it works, I belive..."""
    m_line, q_line = line
    f_1, f_2, double_a, double_b = ellipse
    if f_1 == f_2:
        c_x, c_y = f_1
        radius = double_a / 2
        if m_line is None:
            if abs(q_line - c_x) > radius:
                return None, None
            else:
                radicand = math.pow(radius,2) - math.pow(q_line - c_x, 2)
                var_y = math.sqrt (radicand)
                y_1 = c_y + var_y
                y_2 = c_y - var_y
                x_1 = x_2 = q_line
        else:
            h_1 = 1 + math.pow (m_line, 2)
            h_2 = 2 * (m_line*(q_line - c_y) - c_x)
            h_3 = math.pow(c_x,2) + math.pow(c_y,2) + math.pow(q_line,2) - math.pow(radius,2) - 2*q_line*c_y
            h_2 /= h_1
            h_3 /= h_1
            radicand = math.pow(h_2, 2) - 4 * h_3

            if radicand < 0:
                return None, None
            else:
                x_1 = ( (h_2 * (-1)) + math.sqrt(radicand) ) / 2
                y_1 = m_line * x_1 + q_line
                x_2 = ( (h_2 * (-1)) - math.sqrt(radicand) ) / 2
                y_2 = m_line * x_2 + q_line
    else:
        a = double_a / 2
        b = double_b / 2
        center = (f_1 + f_2) * (1/2)
        c_x, c_y = center
        m_axis, q_axis = line_slope_intercept (f_1, f_2)
        if m_axis is None:
            angle_axis = math.pi / 2
        else:
            angle_axis = math.atan (m_axis)   # radians
        angle = angle_axis *180/math.pi   # degree

        if m_line is None:
            angle_line = math.pi / 2
            p_l = Vec2D(q_line, 0)
        else:
            angle_line = math.atan (m_line)  #radians
            p_l = Vec2D(0, q_line)
        point_line = p_l.rotate (-angle, center)
        angle_rot_line = angle_line - angle_axis  #radians

        if abs(angle_rot_line) == math.pi / 2:  # m_rot_line is None
            if abs(point_line[0] - c_x) > a:
                return None, None
            else:
                radicand = math.pow(a,2) - math.pow(point_line[0] - c_x, 2)
                var_y = b / a * math.sqrt (radicand)
                y_1 = c_y + var_y
                y_2 = c_y - var_y
                x_1 = x_2 = point_line[0]  #q_rot_line
        else:
            m = math.tan(angle_rot_line)          # m_rot_line
            q = point_line[1] - m * point_line[0] # q_rot_line
            a_sqr = math.pow (a, 2)
            b_sqr = math.pow (b, 2)
            m_sqr = math.pow (m, 2)
            q_sqr = math.pow (q, 2)
            c_x_sqr = math.pow (c_x, 2)
            c_y_sqr = math.pow (c_y, 2)
            h_1 = b_sqr + a_sqr * m_sqr
            h_2 = 2 * (a_sqr*m*(q-c_y) - b_sqr*c_x)
            h_3 = b_sqr*c_x_sqr + a_sqr*(q_sqr + c_y_sqr - b_sqr - 2*c_y*q)
            h_2 /= h_1
            h_3 /= h_1
            radicand = math.pow(h_2, 2) - 4 * h_3

            if radicand < 0:
                return None, None
            else:
                x_1 = ( (h_2 * (-1)) + math.sqrt(radicand) ) / 2
                y_1 = m * x_1 + q
                x_2 = ( (h_2 * (-1)) - math.sqrt(radicand) ) / 2
                y_2 = m * x_2 + q
        p_1, p_2 = Vec2D(x_1, y_1), Vec2D(x_2, y_2)
        x_1, y_1 = p_1.rotate (angle, center)
        x_2, y_2 = p_2.rotate (angle, center)
    return Vec2D (truncate(x_1), truncate(y_1)), Vec2D (truncate(x_2), truncate(y_2))

def two_lines_interception (line_1: tuple [float, float], line_2: tuple [float, float]) -> Union [tuple [float, float], tuple [None, None]]:
    m_1, q_1 = line_1
    m_2, q_2 = line_2
    if m_1 == m_2:
        if q_1 == q_2:
            x, y = "All", "points"
        else:
            x, y = None, None
    elif m_1 == None:
        x = q_1
        y = truncate (m_2 * x + q_2)
    elif m_2 == None:
        x = q_2
        y = truncate (m_1 * x + q_1)
    else:
        x = truncate ((q_2 - q_1) / (m_1 - m_2))
        y = truncate (m_1 * x + q_1)
    return x, y

def ellipse_focals_axes (center: tuple [float,float], width: float, height: float, angle: float = 0) -> tuple [Vec2D, Vec2D, float]:
    #the angle is the counterclockwise rotation of axis_1
    axis_1 = width # *SIZE
    axis_2 = height # *SIZE
    if axis_1 == axis_2: # circumference
        f_1 = f_2 = Vec2D (center[0], center[1])
        double_a = double_b = axis_1
    else:
        center = Vec2D (center[0], center[1])
        if max (axis_1, axis_2) == axis_1:
            double_a, double_b = axis_1, axis_2
        else:
            double_a, double_b = axis_2, axis_1
            angle += 90
        c_squared = (math.pow(double_a/2, 2) - math.pow(double_b/2, 2))
        c = truncate(math.sqrt(c_squared)) * SIZE
        f_1 = Vec2D (c, 0)
        f_1 = f_1.rotate(angle)
        f_1 = f_1 + center
        f_1 = Vec2D (truncate(f_1[0]), truncate(f_1[1]))
        f_2 = Vec2D (-c, 0)
        f_2 = f_2.rotate(angle)
        f_2 = f_2 + center
        f_2 = Vec2D (truncate(f_2[0]), truncate(f_2[1]))
    return f_1, f_2, double_a*SIZE, double_b*SIZE

def ellispe_from_squared_label (width: float, height: float) -> tuple[float]:
    """this is based on 2 proprierties of the ellipse:
        - l = math.pow(b,2) / a
        - eccentricity = c/a = math.sqrt(1 - math.pow(b/a, 2))
        the goal is to build an ellipse centered in the origin with focals that intercept
        the label's edges. So we can say that l = height/2 and c = width/2.
        Now, starting by the first proprierty, math.pow(b,2) = l_corner * a -> c/a = math.sqrt(1 - (l/a))
        with a > l_corner and eleveting by 2 each side of the equation leads to math.pow(a,2) - l_corner*a - math.pow(c,2) = 0 ,
        with a delta = math.pow(l,2) + 4*math.pow(c,2) >= math.pow(l,2), so only one solution of the equation is positive.
        """
    width *= SIZE
    height *= SIZE
    l_corner = height/2
    c = width/2
    delta = math.pow(l_corner,2) + 4* math.pow(c,2)
    a = (l_corner + math.sqrt(delta)) /2
    b = math.sqrt(l_corner*a)
    double_a = 2*a
    double_b = 2*b
    return truncate(double_a/SIZE), truncate(double_b/SIZE)

def line_slope_intercept (from_point: tuple[float, float], to_point: tuple[float, float]) -> Union [tuple[float, float], tuple[None, float]]:
    from_x, from_y = from_point
    to_x, to_y = to_point
    if from_x == to_x:
        return None, to_x    # vertical x = q
    else:
        m = truncate ( (to_y - from_y) / (to_x - from_x) )    # slope
        q = truncate ( from_y - (from_x * m) )                # intercept
        return m, q

def rotate_list (lst: list [Vec2D], angle: float, center: tuple[float, float] = (0,0)) -> None:
    for p in range (len(lst)):
        lst[p] = lst[p].rotate (angle, center)
        lst[p] = Vec2D ( truncate(lst[p][0]) , truncate(lst[p][1]) )

def translate_list (lst: list [Vec2D], dir_vect: tuple [float, float]) -> None:
    q = Vec2D(truncate(dir_vect[0]), truncate(dir_vect[1]))
    for p in range (len(lst)):
        lst[p] = Vec2D ( truncate(lst[p][0]) , truncate(lst[p][1]) ) + q

def rototranslate_list (lst: list [Vec2D], dir_vect: tuple[float, float] = None, angle: float = None, center: tuple[float, float] = (0,0)) -> None:
    if angle is not None:
        rotate_list (lst, angle, center)
    if dir_vect is not None:
        translate_list (lst, dir_vect)

def vect_transform_list (lst: list [Vec2D], dir_vect: tuple[float, float] = (1,1), center: tuple[float, float] = (0,0), angle: float = 0) -> None:
    """Takes a relative center and a direction value that, based on each vector's component, represents:
        - the percentage of magnification if is positive
        - the percentage of reduction if is negative and higher than -1
        - the reflection on that axis if lower than -1, in particular:
            - if higher than -2 is a percentual reducted reflection
            - if lower than -2 is a percentual magnification reflection
        By default it duplicates every point of an hypothetical figure centered in the origin.
        The angle is given when the figure has been rotated"""
    d_x, d_y = dir_vect
    center = Vec2D (center[0], center[1])
    c_x, c_y = center
    for p in range (len(lst)):
        lst[p] = lst[p] - center
        lst[p] = lst[p].rotate(-angle)
        var_x = lst[p][0] * (d_x + 1)
        var_y = lst[p][1] * (d_y + 1)
        lst[p] = Vec2D (var_x, var_y)
        lst[p] = lst[p].rotate(angle)
        lst[p] = lst[p] + center
        lst[p] = Vec2D ( truncate(lst[p][0]) , truncate(lst[p][1]) )

#==================================================================================
#============================== END_MATHEMATICAL_FUNCTIONS=========================
#==================================================================================
#============================== GRAPHICAL_FUNCTIONS ===============================
#==================================================================================

def check_new_position (squaredarea, button, position) -> Vec2D:
    x_c, y_c = position
    if button.get_shape() == "circle" and button.is_regular():
        half_width = button.get_width()*SIZE/2
        lt_x = x_c - half_width
        rt_x = x_c + half_width
        lw_y = y_c - half_width
        up_y = y_c + half_width
    else:
        center = button.get_center()
        edges = button.get_edges()
        to = Vec2D (position[0], position[1])
        dir_vect = to - center
        translate_list (edges, dir_vect)
        lt_x, lw_y = to
        rt_x, up_y = to
        for p in edges:
            lt_x = min(lt_x, p[0])
            rt_x = max(rt_x, p[0])
            lw_y = min(lw_y, p[1])
            up_y = max(up_y, p[1])
    left_x, right_x, lower_y, upper_y = squaredarea.get_corners_coordinates()
    if lt_x < left_x + GAP:
        x_c += (left_x + GAP - truncate(lt_x))
    if rt_x > right_x - GAP:
        x_c += (right_x - GAP - truncate(rt_x))
    if lw_y < lower_y + GAP:
        y_c += (lower_y + GAP - truncate(lw_y))
    if up_y > upper_y - GAP:
        y_c += (upper_y - GAP - truncate(up_y))
    return Vec2D(x_c, y_c)

def select_button (x, y) -> Union[Button,Vec2D]:
    point = Vec2D(x,y)
    for button in BUTTONS_LIST:
        #print (f"{point = }, {button.get_center() = }, ", button)
        if point in button and not isinstance(button,SquaredArea):
            return button
    return point

def make_graph_editable (*args) -> None:
    edit_mode()
    for button in BUTTONS_LIST:
        if str(button) == "Edit Graph":
            button.set_label (text = "Stop Edit")
            button.set_action(stop_edit_graph)
            break

def stop_edit_graph (*args) -> None:
    stop_edit_mode()
    for button in BUTTONS_LIST:
        if str(button) == "Stop Edit":
            button.set_label (text = "Edit Graph")
            button.set_action(make_graph_editable)
            break

def _move_node (node, pos) -> bool:
    for button in BUTTONS_LIST:
        if isinstance(button, GraphicalGraph) and button.is_selected():
            vertex = button._graph_vertices
            new_center = check_new_position (button.get_graph_area(), node, pos)
            center = node.get_center()
            node.move_to(new_center)
            wrong_pos = False
            for m in vertex:
                if vertex[m] is not node:
                    wrong_pos = wrong_pos or node.touch(vertex[m])
            if not wrong_pos:
                for link in button._links:
                    if node in link:
                        button._links[link].refresh()
                        button._links[link].show_arrow()
                return True
            else:
                node.move_to(center)
                return False

def move_node (x,y) -> None:
    for button in BUTTONS_LIST:
        if isinstance(button, GraphicalGraph) and button.is_selected():
            s = turtle.Screen()
            vertex = button._graph_vertices
            selected = False
            for v in vertex:
                if vertex[v].is_selected():
                    selected = True
                    node = vertex[v]
                    break
            if not selected:
                node = select_button (x,y)
                if str(node) in vertex: #probably it is meaningless using it
                    node.set_selected(True)
                s.onclick(move_node)
            else:
                pos = select_button (x,y)
                if isinstance (pos, Vec2D):
                    right_point = _move_node(node, pos)
                    if not right_point:
                        s.onclick(move_node)
                    else:
                        vertex[v].set_selected(False)
                        s.onclick(check_action)
                else:
                    s.onclick(move_node)
            break

def edit_mode () -> None:
    for button in BUTTONS_LIST:
        if isinstance(button, GraphicalGraph):
            if not button.is_selected():
                button.hide_button()
            else:
                button.set_action(do_nothing)
                for v in button._graph_vertices:
                    button._graph_vertices[v].set_action(move_node)
        elif str(button) == "Start simulation":
            button.hide_button()

def stop_edit_mode () -> None:
    for button in BUTTONS_LIST:
        if isinstance(button, GraphicalGraph):
            button.show_button()
            if button.is_selected():
                button.set_action(button.new_draw)
                for v in button._graph_vertices:
                    button._graph_vertices[v].set_action(do_nothing)
        elif str(button) == "Start simulation":
            button.show_button()

def go_to_DAG_simulation(*args) -> None:
    #stop_edit_mode()
    selected = False
    for button in BUTTONS_LIST:
        check_1 = str(button) == "Edit Graph"
        check_2 = str(button) == "Start simulation"
        check_3 = isinstance (button, GraphicalGraph)
        if check_1 or check_2 or check_3:
            button.hide_button()
            if check_2:
                button.set_label(text = "Come Back")
                button.set_action(go_to_DAG_page)
                button.move_to((-30, -130))
            if check_3:
                selected = selected or button.is_selected()
                if button.is_selected():
                    g = button
    if not selected:
        go_to_DAG_page (0,0)
    else:
        if verify_DAG (g.get_rawgraph()):
            DAG_simulation()
        else:
            g.goto(-331, -184)
            text = "this is not a directed acyclic graph"
            g.write(text.upper(), font = ('Arial', 20, 'bold'))
            time.sleep (3)
            g.undo()
            go_to_DAG_page (0,0)

def go_to_DAG_page (*args) -> None:
    clear_all()
    DAG_page ()

def clear_all() -> None:
    for button in BUTTONS_LIST:
        if isinstance (button, GraphicalGraph):
            button.hide_graph()
        button.hide_button()
    BUTTONS_LIST.clear()

def graphical_topological_sort (g: GraphicalGraph) -> None:
    s = GStack ('stack'.upper(), (150, 187), direction = 'down')
    s.show_button()
    order = GStack ('list'.upper(), (250, 187), direction = 'down')
    order.show_button()
    vertex = g.get_vertices ()
    for v in vertex:
        vertex[v].set_bgcolor('white')
        vertex[v].set_label (color = 'black')
        vertex[v].refresh()
    for v in vertex:
        if vertex[v].get_bgcolor () != "black":
            graphical_topological_sort_from (vertex[v], g, s, order)
    s.set_label (text = 'order'.upper())
    while not order.isEmpty():
        time.sleep(0.5)
        s.push (order.pop())
    order.hide_button()

def graphical_topological_sort_from (v: Button, g: GraphicalGraph, s: GraphicalStack, order: GraphicalStack) -> None:
    vertex = g.get_vertices()
    s.push (v)
    while s.size () > 0:
        time.sleep(1)
        v_s = str(s.pop ())
        if vertex[v_s].get_bgcolor() == "white":
            time.sleep(1)
            s.push (vertex[v_s])
            vertex[v_s].set_bgcolor ("gray")
            vertex[v_s].refresh()
            for n in g.get_neighbors ( vertex[v_s]):
                time.sleep(1)
                if n.get_bgcolor() == "white":
                    s.push (n)
        elif vertex[v_s].get_bgcolor() == "gray":
            time.sleep(1)
            order.push (vertex[v_s])
            vertex[v_s].set_bgcolor ("black")
            vertex[v_s].set_label (color = "white")
            vertex[v_s].refresh()

#==================================================================================
#============================== END_OF_GRAPHICAL_FUNCTIONS ========================
#==================================================================================
#============================== PAGES_FUNCTIONS ===================================
#==================================================================================

def DAG_page () -> None:
    graphs_list = load_graphs_from_file("Directed_Acyclic_Graph_examples.txt")
    graph_area = SquaredArea (*GRAPH_AREA)
    graph_area.show_area()
    lt_x, rt_x, lw_y, up_y = graph_area.get_corners_coordinates()
    point = Vec2D(150, 187)
    graph_buttons = {}
    for graph in graphs_list:
        graph_buttons[graph] = GraphicalGraph (graph = graphs_list[graph], area = graph_area, label = graph)
        graph_buttons[graph].set_label (size = 20, style = "bold")
        graph_buttons[graph].move_to (point)
        graph_buttons[graph].set_action(graph_buttons[graph].new_draw)
        graph_buttons[graph].show_button()
        y = point[1] - (graph_buttons[graph].get_height()*SIZE + 5)
        if y < lw_y:
            x = point[0] + (graph_buttons[graph].get_width()*SIZE + 20)
            point = Vec2D (x, 187)
        else:
            point = Vec2D(point[0], y)
    #edit_button
    edit = Button (label = "Edit Graph", pos = (-30, -130))
    edit.set_label (size = 20, style = "bold")
    edit.set_action(make_graph_editable)
    edit.show_button()
    #start_simulation_button
    start = Button (label = "Start simulation", pos = (-220, -130))
    start.set_label (size = 20, style = "bold")
    start.set_action(go_to_DAG_simulation)
    start.show_button()
    #exit_button
    exit = Button (label = 'exit'.upper(), pos = (296, -196))
    exit.set_label (size = 20, style = "bold")
    exit.set_action(close_program)
    exit.show_button()

def DAG_simulation() -> None:
    for button in BUTTONS_LIST:
        if isinstance (button, GraphicalGraph) and button.is_selected():
            button.move_to((-220, -130))
            button.show_button()
            button.set_action(do_nothing)
            g = button
        elif str(button) == "Come Back":
            back = button
    graphical_topological_sort(g)
    #print (topological_sort(g.get_rawgraph())) # for debug checking
    back.show_button()

#==================================================================================
#============================== END_OF_PAGES_FUNCTIONS ============================
#==================================================================================

def follow_neighborhood (v: str, g: Graph, lst: list):
    if len(g.neighbors(v)) == 0:
        end = True
    else:
        for n in g.neighbors(v):
            if n in lst:
                end = False
                break
            else:
                lst.append (n)
                end = follow_neighborhood (n, g, lst)
    lst.pop()
    return end

def verify_DAG (g: Graph) -> bool:
    lst = []
    DAG = True
    for v in g.vertices():
        lst.append(v)
        DAG = DAG and follow_neighborhood (v, g, lst)
    return DAG

def topological_sort (g: Graph) -> list:
    color = {}
    order = []
    for v in g.vertices ():
        color [v] = "white"
    for v in g.vertices ():
        if color [v] != "black":
            topological_sort_from (v, g, color, order)
    return order [::-1]

def topological_sort_from (v: str, g: Graph, color: dict, order: list) -> None:
    s = Stack ()
    s.push (v)
    while s.size () > 0:
        v = s.pop ()
        if color [v] == "white":
            s.push (v)
            color [v] = "gray"
            for n in g.neighbors (v):
                if color [n] == "white":
                    s.push (n)
        elif color [v] == "gray":
            order.append (v)
            color [v] = "black"

def load_graphs_from_file (textfile: str) -> dict:
    s = Stack ()
    graphs_list = {}
    f_in = open (textfile, "r")
    for line in f_in:
        line = line.strip()
        lst_line = line.split()
        if lst_line [1] == "start":
            s.push (lst_line[0]) # the name of the graph
            graphs_list[s.peek()] = Graph ()
        elif lst_line [1] == "->":
            if s.isEmpty():
                raise RuntimeError (f"Input file missing of a start statement before {line = }")
            graphs_list[s.peek()].add_vertex(lst_line[0])
            i = 2
            while i < len (lst_line):
                graphs_list[s.peek()].add_edge(lst_line[0],lst_line[i])
                i += 1
        elif lst_line [1] == "stop":
            if s.isEmpty():
                raise RuntimeError (f"Input file is missing of a start statement before {line = }")
            stop = s.pop()
        else:
            raise ValueError (f"{lst_line[1]} isn't the right statement: only 'start', 'stop', and '->' are accepted")
    f_in.close()
    if s.isEmpty():
        return graphs_list
    else:
        raise RuntimeError (f"Input file, on {s.peek} graph, is missing of a stop statement")

def check_action (x,y) -> None:
    button = select_button(x,y)
    #print (button)
    #print(x,y)
    if isinstance(button, Button):
        button.do(x,y)

def do_nothing (x,y) -> None:
    #print(x,y)
    return None

def close_program (x,y) -> None:
    global myWin
    turtle._Screen._destroy(myWin)

def main() -> None:
    global myWin
    myWin = turtle.Screen()
    myWin.setup(width=WINDOW_SIZE[0],height=WINDOW_SIZE[1])
    turtle.tracer (False)

    DAG_page ()

    turtle.update()
    myWin.onclick(check_action)
    myWin.mainloop()

if __name__ == "__main__":
    main()
