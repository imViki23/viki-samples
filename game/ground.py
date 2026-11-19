import arcade
from constants import Constants
from enum import Enum

class Ground:
    def __init__( self, x: float, y: float, 
                 width: float = 1, 
                 height: float = 1,
                 color: arcade.color = arcade.color.RED):
        self.position = arcade.Vec2(x, y)
        self.velocity = arcade.Vec2(0, 0)
        self.acceleration = arcade.Vec2(0, -Constants.GRAVITY)
        self.width = width
        self.height = height
        self.color = color

    def resolve_collision(self, game_object):
        # Simple AABB collision resolution
        left = self.position.x - self.width / 2
        right = self.position.x + self.width / 2
        bottom = self.position.y - self.height / 2
        top = self.position.y + self.height / 2

        obj_left = game_object.position.x - game_object.width / 2
        obj_right = game_object.position.x + game_object.width / 2
        obj_bottom = game_object.position.y - game_object.height / 2
        obj_top = game_object.position.y + game_object.height / 2

        if right < obj_left or left > obj_right or top < obj_bottom or bottom > obj_top:
            return  # No collision

        # Simple collision response: move the object above the ground
        if game_object.velocity.y < 0:
            game_object.position = arcade.Vec2(game_object.position.x, top + game_object.height / 2)
            game_object.velocity = arcade.Vec2(game_object.velocity.x, 0)

    def update(self, delta_time):
        pass

    def draw(self):
        arcade.draw_lbwh_rectangle_filled(
            left=self.position.x * Constants.PPM - self.width * Constants.PPM / 2,
            bottom=self.position.y * Constants.PPM - self.height * Constants.PPM / 2,
            width=self.width * Constants.PPM,
            height=self.height * Constants.PPM,
            color=self.color
        )
 