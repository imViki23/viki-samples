import arcade
from game_object import GameObject, ShapeType
from ground import Ground
from constants import Constants

class Game(arcade.Window):
    def __init__(self):
        super().__init__(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, "Game")
        arcade.set_background_color(arcade.color.BLACK)
        self.elapsed_time = 0
        self.ground = Ground( x = Constants.WORLD_WIDTH / 2, y = 0, width = Constants.WORLD_WIDTH, height = 0.25 )
        self.ball_1 = GameObject( x = Constants.WORLD_WIDTH * 0.5, y = Constants.WORLD_HEIGHT, radius = 0.25 )
        self.ball_2 = GameObject( x = Constants.WORLD_WIDTH * 0.25, y = Constants.WORLD_HEIGHT * 0.25, radius = 0.25 )
        self.ball_2.apply_impulse(arcade.Vec2(4, 15))

    def on_mouse_press(self, x, y, button, modifiers):
        world_x = x / Constants.PPM
        world_y = y / Constants.PPM
        return super().on_mouse_press(x, y, button, modifiers)

    def on_update(self, delta_time):
        self.elapsed_time += delta_time
        self.ball_1.update(delta_time)
        self.ball_2.update(delta_time)

        # Resolve collision with the ground
        self.ground.resolve_collision(self.ball_1)
        self.ground.resolve_collision(self.ball_2)

    def on_draw(self):
        self.clear()

        arcade.draw_text(f"World width: {Constants.WORLD_WIDTH}", 10, Constants.SCREEN_HEIGHT - 20, arcade.color.WHITE, 10)
        arcade.draw_text(f"World height: {Constants.WORLD_HEIGHT}", 10, Constants.SCREEN_HEIGHT - 40, arcade.color.WHITE, 10)
        arcade.draw_text(f"Elapsed Time: {self.elapsed_time:.2f}", 10, Constants.SCREEN_HEIGHT - 60, arcade.color.WHITE, 10)

        self.ball_1.draw()
        self.ball_2.draw()
        self.ground.draw()

def main():
    game = Game()
    arcade.run()

if __name__ == "__main__":
    main()