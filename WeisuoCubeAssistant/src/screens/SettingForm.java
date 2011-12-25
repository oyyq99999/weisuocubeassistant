package screens;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;

import model.GlobalData;

public class SettingForm extends Form implements CommandListener,
		ItemStateListener {

	private final Command okCommand = new Command("确定", Command.OK, 1);
	private final Command cancelCommand = new Command("取消", Command.CANCEL, 1);
	// private final Command chooseCommand = new Command("", Command.ITEM, 1);
	private Displayable former;
	private final String[] scramblersFor222 = { "随机状态", "随机步骤" };
	private final String[] scramblersFor333 = { "随机状态", "随机步骤" };
	private final String[] scramblersForPyraminx = { "随机状态", "随机步骤" };
	private ChoiceGroup scramblerChoiceFor222 = new ChoiceGroup("二阶打乱方式",
			ChoiceGroup.EXCLUSIVE, scramblersFor222, null);
	private ChoiceGroup scramblerChoiceFor333 = new ChoiceGroup("三阶打乱方式",
			ChoiceGroup.EXCLUSIVE, scramblersFor333, null);
	private ChoiceGroup scramblerChoiceForPyraminx = new ChoiceGroup("金字塔打乱方式",
			ChoiceGroup.EXCLUSIVE, scramblersForPyraminx, null);
	private TextField randomPosition222MinLength = new TextField("最短打乱步骤", "0",
			1, TextField.NUMERIC);
	private TextField randomPosition333MaxLength = new TextField("最长打乱步骤",
			"25", 2, TextField.NUMERIC);
	private TextField randomPositionPyraminxMinLength = new TextField("最短打乱步骤",
			"0", 1, TextField.NUMERIC);

	public SettingForm(Displayable former) {
		super("设置");
		this.addCommand(okCommand);
		this.addCommand(cancelCommand);
		// scramblerChoiceFor222.addCommand(chooseCommand);
		// scramblerChoiceFor333.addCommand(chooseCommand);
		// scramblerChoiceForPyraminx.addCommand(chooseCommand);
		this.setCommandListener(this);
		this.setItemStateListener(this);
		this.former = former;
		// TODO Auto-generated constructor stub
	}

	public void refresh() {
		this.deleteAll();
		if (GlobalData.randomPosition222) {
			scramblerChoiceFor222.setSelectedIndex(0, true);
		} else
			scramblerChoiceFor222.setSelectedIndex(1, true);
		this.append(scramblerChoiceFor222);
		if (GlobalData.randomPosition222) {
			randomPosition222MinLength.setString(Integer
					.toString(GlobalData.randomPosition222MinLength));
			this.append(randomPosition222MinLength);
		}

		if (GlobalData.randomPosition333) {
			scramblerChoiceFor333.setSelectedIndex(0, true);
		} else
			scramblerChoiceFor333.setSelectedIndex(1, true);
		this.append(scramblerChoiceFor333);
		if (GlobalData.randomPosition333) {
			randomPosition333MaxLength.setString(Integer
					.toString(GlobalData.randomPosition333MaxLength));
			this.append(randomPosition333MaxLength);
		}

		if (GlobalData.randomPositionPyraminx) {
			scramblerChoiceForPyraminx.setSelectedIndex(0, true);
		} else
			scramblerChoiceForPyraminx.setSelectedIndex(1, true);
		this.append(scramblerChoiceForPyraminx);
		if (GlobalData.randomPositionPyraminx) {
			randomPositionPyraminxMinLength.setString(Integer
					.toString(GlobalData.randomPositionPyraminxMinLength));
			this.append(randomPositionPyraminxMinLength);
		}
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if (c == okCommand) {
			if (scramblerChoiceFor333.isSelected(0)) {
				if (Integer.parseInt(randomPosition333MaxLength.getString()) < 20) {
					Alert alert = new Alert("不可接受的长度",
							"三阶最长打乱长度需要至少设置为20步以上，否则可能无解！", null,
							AlertType.ERROR);
					alert.setTimeout(3000);
					GlobalData.display.setCurrent(alert, this);
					return;
				}
				if (Integer.parseInt(randomPosition333MaxLength.getString()) < 22) {
					Alert alert = new Alert("不推荐的长度",
							"三阶最长打乱长度设置为22步以下可能会导致求解很慢！", null,
							AlertType.WARNING);
					alert.setTimeout(3000);
					GlobalData.display.setCurrent(alert, former);
				}
			}
			GlobalData.randomPosition222 = scramblerChoiceFor222.isSelected(0);
			GlobalData.randomPosition333 = scramblerChoiceFor333.isSelected(0);
			GlobalData.randomPositionPyraminx = scramblerChoiceForPyraminx
					.isSelected(0);
			if (GlobalData.randomPosition222MinLength != (byte) (Integer
					.parseInt(randomPosition222MinLength.getString()))) {
				GlobalData.randomPosition222MinLength = (byte) (Integer
						.parseInt(randomPosition222MinLength.getString()));
				GlobalData.scrambler222 = null;
			}
			if (GlobalData.randomPosition333MaxLength != (byte) (Integer
					.parseInt(randomPosition333MaxLength.getString()))) {
				GlobalData.randomPosition333MaxLength = (byte) (Integer
						.parseInt(randomPosition333MaxLength.getString()));
				GlobalData.randomStateScrambler333 = null;
			}
			if (GlobalData.randomPositionPyraminxMinLength != (byte) (Integer
					.parseInt(randomPositionPyraminxMinLength.getString()))) {
				GlobalData.randomPositionPyraminxMinLength = (byte) (Integer
						.parseInt(randomPositionPyraminxMinLength.getString()));
				GlobalData.scramblerPyraminx = null;
			}
		}
		if (c == cancelCommand) {

		}
		if (scramblerChoiceFor333.isSelected(1)
				|| Integer.parseInt(randomPosition333MaxLength.getString()) >= 22) {
			GlobalData.display.setCurrent(former);
		}
	}

	public void itemStateChanged(Item item) {
		// TODO Auto-generated method stub
		if (item instanceof ChoiceGroup) {
			this.deleteAll();
			this.append(scramblerChoiceFor222);
			if (scramblerChoiceFor222.getSelectedIndex() == 0)
				this.append(randomPosition222MinLength);

			this.append(scramblerChoiceFor333);
			if (scramblerChoiceFor333.getSelectedIndex() == 0)
				this.append(randomPosition333MaxLength);

			this.append(scramblerChoiceForPyraminx);
			if (scramblerChoiceForPyraminx.getSelectedIndex() == 0)
				this.append(randomPositionPyraminxMinLength);
		}
	}

}
