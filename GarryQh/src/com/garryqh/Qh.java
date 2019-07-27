package com.garryqh;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.common.collect.Lists;

public class Qh extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		Bukkit.getServer().getConsoleSender().sendMessage("§7[§aGarryQh§7] §a插件正常加载。");
		Bukkit.getServer().getPluginCommand("qh").setExecutor(this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c请不要在控制台使用！");
			return true;
		}
		Player p = (Player) sender;
		ItemStack item = new ItemStack(Material.DIAMOND);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = Lists.newArrayList();
		lore.add("§7- §a强化石");
		lore.add("§7- §e使用方法：");
		lore.add("§7- §c手持强化武器进行强化。");
		lore.add("§7- §4§n请勿叠加放置！");
		lore.add("§b指令：§e/qh nb");
		meta.setLore(lore);
		meta.setDisplayName("§6§l强化石");
		item.setItemMeta(meta);
		if (args.length == 0) {
			p.sendMessage("§b§l强化 §6§l>>> §a获取强化石： §e/qh give");
			p.sendMessage("§b§l强化 §6§l>>> §a强化：§e/qh nb");
			return true;
		}
		if (args[0].equalsIgnoreCase("give") && p.hasPermission("")) {
			p.getInventory().addItem(item);
			return true;
		}
		if (args[0].equalsIgnoreCase("nb")) {
			if (p.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)) {
				Inventory inv = p.getInventory();
				if ((inv.contains(item))) {
					p.sendMessage("§b§l强化 §6§l>>> §a强化成功！");
					ItemStack pl = new ItemStack(p.getInventory().getItemInMainHand());
					ItemMeta plm = pl.getItemMeta();
					int i = plm.getEnchantLevel(Enchantment.DAMAGE_ALL);
					plm.addEnchant(Enchantment.DAMAGE_ALL, i + 1, true);
					pl.setItemMeta(plm);
					p.getInventory().setItemInMainHand(pl);
					inv.removeItem(item);
					return true;
				} else {
					p.sendMessage("§b§l强化 §6§l>>> §c您的强化石呢？想白嫖吗？");
					return true;
				}
			} else {
				p.sendMessage("§b§l强化 §6§l>>> §c请手持钻石剑！");
				return true;
			}
		}
		return false;
	}
}
