package org.aladdin.dat;

import java.nio.ByteBuffer;

import org.aladdin.levels.Block;
import org.aladdin.levels.Level;
import org.aladdin.levels.Modifier;
import org.aladdin.levels.Screen;

public class LevelResource extends Resource {
	public Level level;

	public LevelResource(ByteBuffer buffer, IndexItem index) {
		super(buffer, index);
		readLevel();
	}

	public LevelResource(Resource res) {
		super(res);
		readLevel();
	}

	@Override
	public ResourceType getType() {
		return ResourceType.LEVEL;
	}

	private void readLevel() {
		level = new Level();

		int i, j;

		for (i = 0; i < 24; i++) {
			Screen s = level.addScreen();

			buffer.position(i * 30);
			for (j = 0; j < 30; j++) {
				s.blocks.add(Block.get(buffer.get() & 0xff));
			}

			buffer.position(720 + i * 30);
			for (j = 0; j < 30; j++) {
				s.modifiers.add(Modifier.get(s.blocks.get(j).group,
						buffer.get() & 0xff));
			}
		}
	}
}
