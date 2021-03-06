/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jp.l1j.server.command.executor;

import java.util.StringTokenizer;
import java.util.logging.Logger;
import static jp.l1j.locale.I18N.*;
import jp.l1j.server.model.instance.L1PcInstance;
import jp.l1j.server.packets.server.S_SkillIconGFX;
import jp.l1j.server.packets.server.S_SystemMessage;

public class L1Icon implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Icon.class.getName());

	private L1Icon() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Icon();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringTokenizer st = new StringTokenizer(arg);
			int sprid = Integer.parseInt(st.nextToken(), 10);
			int count = Integer.parseInt(st.nextToken(), 10);
			for (int i = 0; i < count; i++) {
				pc.sendPackets(new S_SkillIconGFX(75, sprid));
				//pc.sendPackets(new S_SkillBrave(pc.getId(), sprid, 20));
				//pc.sendPackets(new S_SkillHaste(pc.getId(), sprid, 20));				
				//pc.sendPackets(new S_PacketBox(53, sprid, 10));
				//pc.sendPackets(new S_SkillIconAura(sprid, 12));				
				pc.sendPackets(new S_SystemMessage("" + sprid));
				sprid = sprid + 1;
				for (int loop = 0; loop < 1; loop++) { // 2秒待機
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(String.format(I18N_COMMAND_FORMAT_2,
					cmdName, I18N_GFX_ID, I18N_AMOUNT)));
			// .%s %s %s の形式で入力してください。
		}
	}
}