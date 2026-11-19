import arcade
from constants import Constants
from enum import Enum

class ShapeType(Enum):
    FILLED_CIRCLE = 1
    FILLED_RECTANGLE = 2

class GameObject:
    def __init__( self, x: float, y: float, 
                 mass: float = 1,
                 radius: float = 1, 
                 width: float = 1, 
                 height: float = 1,
                 gravity: float = Constants.GRAVITY,
                 color: arcade.color = arcade.color.RED,
                 shape: ShapeType = ShapeType.FILLED_CIRCLE):
        self.position = arcade.Vec2(x, y)
        self.velocity = arcade.Vec2(0, 0)
        self.gravity = gravity
        self.acceleration = arcade.Vec2(0, -gravity)
        self.radius = radius
        self.width = width
        self.height = height
        self.color = color
        self.shape = shape
        self.mass = mass
        if shape == ShapeType.FILLED_CIRCLE:
            self.width = self.radius * 2
            self.height = self.radius * 2

    def update(self, delta_time):
        self.velocity += self.acceleration * delta_time
        self.position += self.velocity * delta_time

        self.acceleration = arcade.Vec2(0, -self.gravity)

    def draw(self):
        if self.shape == ShapeType.FILLED_CIRCLE:
            arcade.draw_circle_filled(
                center_x=self.position.x * Constants.PPM,
                center_y=self.position.y * Constants.PPM,
                radius=self.radius * Constants.PPM,
                color=self.color
            )
        elif self.shape == ShapeType.FILLED_RECTANGLE:
            arcade.draw_lbwh_rectangle_filled(
                left=self.position.x * Constants.PPM - self.width * Constants.PPM / 2,
                bottom=self.position.y * Constants.PPM - self.height * Constants.PPM / 2,
                width=self.width * Constants.PPM,
                height=self.height * Constants.PPM,
                color=self.color
            )

    def apply_force(self, force: arcade.Vec2):
        self.acceleration += force / self.mass

    def apply_impulse(self, impulse: arcade.Vec2):
        self.velocity += impulse / self.mass
 